package com.ezen.doran.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.ReDTO;
import com.ezen.doran.dto.ShareDTO;
import com.ezen.doran.service.re.ReService;
import com.ezen.doran.service.share.ShareService;

@RestController
@RequestMapping("/share")
public class ShareController {
	@Autowired
	ShareService shareService;
	
	@Autowired
	ReService reService;
	
	@RequestMapping("/selectShareList")
	public ModelAndView selectShareList(@RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		ModelAndView mv = new ModelAndView();

		// 총 게시글 수
		int totalListCnt = shareService.selectShareCnt(searchKeyword);

		if (page == 0) {
			page = 1;
		} else if (page > totalListCnt) {
			page = totalListCnt;
		}

		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(totalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);

		List<ShareDTO> selectShareList = shareService.selectShareList(pagination);

		mv.addObject("selectShareList", selectShareList);
		mv.addObject("pagination", pagination);
		mv.setViewName("share/selectShareList.html");

		return mv;
	}	
	@RequestMapping("/insertSharePage")
	public ModelAndView insertSharePage() {
		ModelAndView mv = new ModelAndView();
		ShareDTO testDTO = new ShareDTO();
		mv.addObject("share", testDTO);
		mv.addObject("update",false);
		mv.setViewName("share/insertShare.html");
		
		return mv;
	}
	
	@PostMapping("/insertShare")
	public void insertShare(ShareDTO shareDTO, HttpServletResponse response) throws Exception {
		shareService.insertShare(shareDTO);
		response.sendRedirect("/share/selectShareList");
	}
	
	@RequestMapping("/selectShare")
	public ModelAndView selectShare(@RequestParam("shareNo") int shareNo ) {
		ModelAndView mv = new ModelAndView();
		
		ReDTO reDTO = new ReDTO();
		reDTO.setBoardCd("SHR");
		reDTO.setBoardNo(shareNo);
		
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
		
		mv.addObject("share", shareService.selectShare(shareNo));
		
		mv.setViewName("share/selectShare.html");
		return mv;
	}
	
	@RequestMapping("/updateSharePage")
	public ModelAndView updateSharePage(@RequestParam("shareNo") int shareNo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("share", shareService.selectShare(shareNo));
		mv.addObject("update",true);
		mv.setViewName("share/insertShare.html");
		
		return mv;
	}
	
	@RequestMapping("/updateShare")
	public void updateshare(ShareDTO shareDTO, HttpServletResponse response) throws Exception {
		shareService.updateShare(shareDTO);
		response.sendRedirect("/share/selectShare?shareNo="+shareDTO.getShareNo());
	}
	
	@RequestMapping("/deleteShare")
	public void deleteShare(@RequestParam("shareNo") int shareNo, HttpServletResponse response) throws Exception {
		shareService.deleteShare(shareNo);
		response.sendRedirect("/share/selectShareList");
	}
}
