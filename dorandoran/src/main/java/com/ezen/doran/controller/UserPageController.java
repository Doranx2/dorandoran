package com.ezen.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.service.userPage.UserPageService;

@RestController
@RequestMapping("/userPage")
public class UserPageController {
	
	@Autowired
	UserPageService UserPageService;
	
//	@RequestMapping(value="/mypage.do")
//	public String mypage() throws Exception {
//		return "/userPage/mypage";
//	}
	
	@RequestMapping("/mypage")
	public ModelAndView mypage() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/mypage.html");
		return mv;
	}
	
	
	@RequestMapping("/updateUserPage")
	public ModelAndView updateUser() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/updateUserPage.html");
		return mv;
	}
	
	@RequestMapping("/playListPage")
	public ModelAndView myPlayList() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/playListPage.html");
		return mv;
	}
	
	@RequestMapping("/marketListPage")
	public ModelAndView myMarketList() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/marketListPage.html");
		return mv;
	}
	
	@RequestMapping("/writingListPage")
	public ModelAndView myWritingList() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/writingListPage.html");
		return mv;
	}
	
	@RequestMapping("/questionListPage")
	public ModelAndView myQuestionList() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/questionListPage.html");
		return mv;
	}
	
	
	// selectUser
	
	// updateUser(UserDTO)
	
	// selectMyMarketList(int, String)
	
	
	// selectMyHelpList(int, String)
	
	
	// selectMyShareList(int)
	
	
	// selectMyPlayList(int)
	
	
	// selectMyQuestionList(int)
	
	
	// selectMyRepList(int)
}
