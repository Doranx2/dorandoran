package com.ezen.doran.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.doran.service.userPage.UserPageService;

public class UserPageController {
	
	@Autowired
	UserPageService UserPageService;
	
	// selectUser
	@RequestMapping(value = "/userPage", method = RequestMethod.GET)
	public String selectUser(HttpServletRequest request) {
		
		return "userPage/mypage";
	}
	
	
	
	// updateUser(UserDTO)
	
	
	// deleteUser(int)
	
	
	// selectMyMarketList(int, String)
	
	
	// selectMyHelpList(int, String)
	
	
	// selectMyShareList(int)
	
	
	// selectMyPlayList(int)
	
	
	// selectMyQuestionList(int)
	
	
	// selectMyRepList(int)
}
