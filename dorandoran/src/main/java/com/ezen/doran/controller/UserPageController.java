package com.ezen.doran.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.Pagination2;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.ShareDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.service.userPage.UserPageService;

@RestController
@RequestMapping("/userPage")
public class UserPageController {
	
	@Autowired
	UserPageService UserPageService;
	
	@RequestMapping("/updateUserPage")
	public ModelAndView updateUser() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/updateUserPage.html");
		return mv;
	}
	
	
	// updateUser(UserDTO)
	@PostMapping("/updateUser")
	public void updateUser(UserDTO UserDTO, HttpServletResponse response) throws Exception {
		UserPageService.updateUser(UserDTO);
		
		response.sendRedirect("/userPage/updateUserPage");
	}
	

	
	// selectMyMarketList(int, String)
	@RequestMapping("/marketListPage")
	public ModelAndView selectMyMarketList(@RequestParam("userNo") int userNo,
			@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();
		System.out.println("userNo:"+userNo);
		
		int myMarketListCnt = UserPageService.selectMyMarketListCnt(userNo);
		System.out.println("myMarketListCnt:"+myMarketListCnt);
		
		if (page == 0) {
			page = 1;
		} else if (page > myMarketListCnt) {
			page = myMarketListCnt;
		}
		
		Pagination pagination = new Pagination(myMarketListCnt, page);
		
		pagination.setUserNo(userNo);
		
		List<MarketDTO> selectMyMarketList = UserPageService.selectMyMarketList(pagination);
		
		mv.addObject("myMarket", selectMyMarketList);
		mv.addObject("pagination", pagination);
		mv.setViewName("/userPage/marketListPage.html");
		
		
		return mv;
	}
	
	
	
//	 selectMyWritingList(int) - share, help, play
	@RequestMapping("/writingListPage")
	public ModelAndView selectMyWritingList(@RequestParam("userNo") int userNo,
			@RequestParam(defaultValue = "1") int page1, @RequestParam(defaultValue = "1") int page2, @RequestParam(defaultValue = "1") int page3) {
		ModelAndView mv = new ModelAndView();
		
		int myShareListCnt = UserPageService.selectMyShareListCnt(userNo);
		int myHelpListCnt = UserPageService.selectMyHelpListCnt(userNo);
		int myPlayListCnt = UserPageService.selectMyPlayListCnt(userNo);
		
		System.out.println("myMarketListCnt:"+myShareListCnt);
		System.out.println("myHelpListCnt:"+myHelpListCnt);
		System.out.println("myPlayListCnt:"+myPlayListCnt);
		
		if (page1 == 0) {
			page1 = 1;
		} else if (page1 > myShareListCnt) {
			page1 = myShareListCnt;
		}
		
		if (page2 == 0) {
			page2 = 1;
		} else if (page2 > myHelpListCnt) {
			page2 = myHelpListCnt;
		}
		
		if (page3 == 0) {
			page3 = 1;
		} else if (page3 > myPlayListCnt) {
			page3 = myPlayListCnt;
		}
		
		Pagination2 pagination1 = new Pagination2(myShareListCnt, page1);
		Pagination2 pagination2 = new Pagination2(myHelpListCnt, page2);
		Pagination2 pagination3 = new Pagination2(myPlayListCnt, page3);
		
		pagination1.setUserNo(userNo);
		pagination2.setUserNo(userNo);
		pagination3.setUserNo(userNo);
		
		List<ShareDTO> selectMyShareList = UserPageService.selectMyShareList(pagination1);
		List<HelpDTO> selectMyHelpList = UserPageService.selectMyHelpList(pagination2);
		List<PlayDTO> selectMyPlayList = UserPageService.selectMyPlayList(pagination2);
		
		mv.addObject("myShare", selectMyShareList);
		mv.addObject("myHelp", selectMyHelpList);
		mv.addObject("myPlay", selectMyPlayList);
		mv.addObject("pagination1", pagination1);
		mv.addObject("pagination2", pagination2);
		mv.addObject("pagination3", pagination3);
		
		mv.setViewName("/userPage/writingListPage.html");
		return mv;
	}
	
	
	
	// selectMyQuestionList(int)
	// selectMyRepList(int)
	@RequestMapping("/questionListPage")
	public ModelAndView selectMyQuestionList(@RequestParam("userNo") int userNo,
			@RequestParam(defaultValue = "1") int page1, @RequestParam(defaultValue = "1") int page2) {
		ModelAndView mv = new ModelAndView();
		

		
		int myQuestionListCnt = UserPageService.selectMyQuestionListCnt(userNo);
		int myRepListCnt = UserPageService.selectMyRepListCnt(userNo);
		
		System.out.println("myQuestionListCnt:"+myQuestionListCnt);
		System.out.println("myRepListCnt:"+myRepListCnt);
		
		if (page1 == 0) {
			page1 = 1;
		} else if (page1 > myQuestionListCnt) {
			page1 = myQuestionListCnt;
		}
		
		if (page2 == 0) {
			page2 = 1;
		} else if (page2 > myRepListCnt) {
			page2 = myRepListCnt;
		}
		
		Pagination2 pagination1 = new Pagination2(myQuestionListCnt, page1);
		Pagination2 pagination2 = new Pagination2(myRepListCnt, page2);
		
		pagination1.setUserNo(userNo);
		pagination2.setUserNo(userNo);
		
		List<QuestionDTO> selectMyQuestionList = UserPageService.selectMyQuestionList(pagination1);
		List<RepDTO> selectMyRepList = UserPageService.selectMyRepList(pagination2);
		
		mv.addObject("myQuestion", selectMyQuestionList);
		mv.addObject("myRep", selectMyRepList);
		mv.addObject("pagination1", pagination1);
		mv.addObject("pagination2", pagination2);
		mv.setViewName("/userPage/questionListPage.html");
		
		System.out.println(selectMyQuestionList);
		
		return mv;
	}
	
	
	
	// selectMyJoinList(int)
	@RequestMapping("/playListPage")
	public ModelAndView selectMyJoinList(@RequestParam("userNo") int userNo,
			@RequestParam(defaultValue = "1") int page1, @RequestParam(defaultValue = "1") int page2) {
		ModelAndView mv = new ModelAndView();
		
		int myJoinListCnt = UserPageService.selectMyJoinListCnt(userNo);
		
		System.out.println("myJoinListCnt:"+myJoinListCnt);
		
		if (page1 == 0) {
			page1 = 1;
		} else if (page1 > myJoinListCnt) {
			page1 = myJoinListCnt;
		}
		
		Pagination2 pagination1 = new Pagination2(myJoinListCnt, page1);
		
		pagination1.setUserNo(userNo);
		
		List<JoinDTO> selectMyJoinList = UserPageService.selectMyJoinList(pagination1);
		
		mv.addObject("myJoin", selectMyJoinList);
		mv.addObject("pagination1", pagination1);
		
		mv.setViewName("/userPage/playListPage.html");
		return mv;
	}
	

}
