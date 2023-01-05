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
	
	// selectUser
//	@RequestMapping("/selectUser")
//	public ModelAndView selectUser(@RequestParam int joinNo) {
//		ModelAndView mv = new ModelAndView();
//		
//	}
	
	
	// updateUser(UserDTO)
//	@RequestMapping("/updateUser")
//	public ModelAndView selectUser(@RequestParam int joinNo) {
//		ModelAndView mv = new ModelAndView();
//		
//	}
	
	
	// deleteUser(int)
//	@RequestMapping("/deletetUser")
//	public ModelAndView selectUser(@RequestParam int joinNo) {
//		ModelAndView mv = new ModelAndView();
//		
//	}
	
	
	// selectMyMarketList(int, String)
	
	
	// selectMyHelpList(int, String)
	
	
	// selectMyShareList(int)
	
	
	// selectMyPlayList(int)
	
	
	// selectMyQuestionList(int)
	
	
	// selectMyRepList(int)
}
