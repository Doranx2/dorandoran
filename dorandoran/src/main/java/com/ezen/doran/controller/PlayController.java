package com.ezen.doran.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.service.play.PlayService;

@RestController
@RequestMapping("/play")
public class PlayController {

	@Autowired
	PlayService playService;

	@PostMapping("/insertPlay")
	public void insertPlay(PlayDTO playDTO, HttpServletResponse response) throws IOException {
		playDTO.setUserNo(1);

		playService.insertPlay(playDTO);
		response.sendRedirect("/");
	}

	@RequestMapping("/selectPlayList")
	public ModelAndView selectPlayList(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();

		// 총 게시글 수
		int totalListCnt = playService.selectPlayCnt();

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);

		List<PlayDTO> selectPlayList = playService.selectPlayList(pagination);

		System.out.println(selectPlayList);
		mv.addObject("selectPlayList", selectPlayList);
		mv.addObject("pagination", pagination);
		mv.setViewName("play/selectPlayList.html");

		return mv;
	}
	
	@RequestMapping("/selectPlay")
	public ModelAndView selectPlay(@RequestParam int playNo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("play", playService.selectPlay(playNo));
		
		mv.setViewName("play/selectPlay.html");
		return mv;
	}

}
