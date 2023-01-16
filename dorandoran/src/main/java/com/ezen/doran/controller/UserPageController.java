package com.ezen.doran.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
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
			@RequestParam(defaultValue = "1") int page, Model model) {
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
		
		model.addAttribute("userNo", userNo);
		model.addAttribute("pagination", pagination);
		
		List<MarketDTO> selectMyMarketList = UserPageService.selectMyMarketList(userNo, pagination);
		
		mv.addObject("myMarket", selectMyMarketList);
		mv.addObject("pagination", pagination);
		mv.setViewName("/userPage/marketListPage.html");
		
		
		return mv;
	}
	
	
	
//	 selectMyWritingList(int) - share, help, play
	@RequestMapping("/writingListPage")
	public ModelAndView selectMyWritingList(@RequestParam("userNo") int userNo,
			@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();
		
		List<ShareDTO> selectMyShareList = UserPageService.selectMyShareList(userNo);
		List<HelpDTO> selectMyHelpList = UserPageService.selectMyHelpList(userNo);
		List<PlayDTO> selectMyPlayList = UserPageService.selectMyPlayList(userNo);
		
		int myShareListCnt = UserPageService.selectMyShareListCnt(userNo);
		int myHelpListCnt = UserPageService.selectMyHelpListCnt(userNo);
		int myPlayListCnt = UserPageService.selectMyPlayListCnt(userNo);
		
		System.out.println("myMarketListCnt:"+myShareListCnt);
		System.out.println("myHelpListCnt:"+myHelpListCnt);
		System.out.println("myPlayListCnt:"+myPlayListCnt);
		
		mv.addObject("myShare", selectMyShareList);
		mv.addObject("myHelp", selectMyHelpList);
		mv.addObject("myPlay", selectMyPlayList);
		
		mv.setViewName("/userPage/writingListPage.html");
		return mv;
	}
	
	
	
	// selectMyQuestionList(int)
	// selectMyRepList(int)
	@RequestMapping("/questionListPage")
	public ModelAndView selectMyQuestionList(@RequestParam("userNo") int userNo) {
		ModelAndView mv = new ModelAndView();
		
		List<QuestionDTO> selectMyQuestionList = UserPageService.selectMyQuestionList(userNo);
		List<RepDTO> selectMyRepList = UserPageService.selectMyRepList(userNo);
		
		int myQuestionListCnt = UserPageService.selectMyQuestionListCnt(userNo);
		int myRepListCnt = UserPageService.selectMyRepListCnt(userNo);
		
		System.out.println("myQuestionListCnt:"+myQuestionListCnt);
		System.out.println("myRepListCnt:"+myRepListCnt);
		
		mv.addObject("myQuestion", selectMyQuestionList);
		mv.addObject("myRep", selectMyRepList);
		mv.setViewName("/userPage/questionListPage.html");
		
		System.out.println(selectMyQuestionList);
		
		return mv;
	}
	
	
	
	// selectMyJoinList(int)
	@RequestMapping("/playListPage")
	public ModelAndView selectMyJoinList(@RequestParam("userNo") int userNo) {
		ModelAndView mv = new ModelAndView();
		
		List<JoinDTO> selectMyJoinList = UserPageService.selectMyJoinList(userNo);
		
		int myJoinListCnt = UserPageService.selectMyJoinListCnt(userNo);
		
		System.out.println("myJoinListCnt:"+myJoinListCnt);
		
		mv.addObject("myJoin", selectMyJoinList);
		
		mv.setViewName("/userPage/playListPage.html");
		return mv;
	}
	

}
