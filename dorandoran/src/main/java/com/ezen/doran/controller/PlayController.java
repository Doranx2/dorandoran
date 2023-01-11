package com.ezen.doran.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.service.play.PlayService;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/play")
public class PlayController {

	@Autowired
	PlayService playService;
	
	@Autowired
	ReService reService;

	@GetMapping("/insertPlayPage")
	public ModelAndView insertPlayPage() {
		ModelAndView mv = new ModelAndView();
		PlayDTO testDTO = new PlayDTO();
		mv.addObject("play", testDTO);
		mv.addObject("update",false);
		mv.setViewName("play/insertPlay.html");
		
		return mv;
	}
	
	@GetMapping("/updatePlayPage")
	public ModelAndView updatePlayPage(@RequestParam int playNo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("play", playService.selectPlay(playNo));
		mv.addObject("update",true);
		mv.setViewName("play/insertPlay.html");
		
		return mv;
	}
	
	@RequestMapping("/updatePlay")
	public void updatePlay(PlayDTO playDTO, HttpServletResponse response) throws Exception {
		playService.updatePlay(playDTO);
		response.sendRedirect("/play/selectPlay?playNo=" + playDTO.getPlayNo());
	}
	
	@PostMapping("/insertPlay")
	public void insertPlay(PlayDTO playDTO, HttpServletResponse response) throws Exception {
		playService.insertPlay(playDTO);
		response.sendRedirect("/play/selectPlayList");
	}

	@RequestMapping("/selectPlayList")
	public ModelAndView selectPlayList(@RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		ModelAndView mv = new ModelAndView();

		// 총 게시글 수
		int totalListCnt = playService.selectPlayCnt(searchKeyword);

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);

		List<PlayDTO> selectPlayList = playService.selectPlayList(pagination);

		mv.addObject("selectPlayList", selectPlayList);
		mv.addObject("pagination", pagination);
		mv.setViewName("play/selectPlayList.html");

		return mv;
	}
	
	@RequestMapping("/selectPlay")
	public ModelAndView selectPlay(@RequestParam int playNo) {
		ModelAndView mv = new ModelAndView();
		
		ReDTO reDTO = new ReDTO();
		reDTO.setBoardCd("PLY");
		reDTO.setBoardNo(playNo);
		
		List<ReDTO> list = reService.selectReList(reDTO);
		List<ReDTO> reList = new ArrayList<>();
		List<ReDTO> reReList = new ArrayList<>();
		
		
		for(ReDTO re : list) {
			if(re.getParentReNo() != 0) {
				reReList.add(re);
			} else if(re.getParentReNo()==0) {
				reList.add(re);
			}
		}
		
		System.out.println("reList => " +reList);
		System.out.println("reReList => " +reReList);

		mv.addObject("reList", reList);
		mv.addObject("reReList", reReList);
		
		mv.addObject("play", playService.selectPlay(playNo));
		
		mv.setViewName("play/selectPlay.html");
		return mv;
	}
	
	@RequestMapping("/updatePlayCnt")
	public void updatePlayCnt(@RequestParam int playNo, HttpServletResponse response) throws Exception {
		playService.updatePlayCnt(playNo);
		response.sendRedirect("/play/selectPlay?playNo="+playNo);
	}
	
	@GetMapping("/deletePlay")
	public void deletePlay(@RequestParam int playNo, HttpServletResponse response) throws Exception {
		playService.deletePlay(playNo);
		response.sendRedirect("/play/selectPlayList");
	}
	

}
