package com.ezen.doran.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.service.temp.tempService;

@RestController
@RequestMapping("/temp")
public class TempController {
	
	@Autowired
	tempService tempService;
	
	@RequestMapping("/login")
	public boolean selectUser(@RequestParam String userId,@RequestParam String userPw, HttpSession httpSession) {
		UserDTO user = tempService.selectUser(userId);
		System.out.println(user);
		
		if(user==null) {
			return false;
		}
		
		if(user.getUserPw() != null && user.getUserPw().equals(userPw)) {
			httpSession.setAttribute("loginUser", user);
			return true;
		} else {
			return false;
		}
		
//		ModelAndView mv = new ModelAndView();
		
//		mv.setViewName("/index.html");
//		return mv;
	}
	
	@RequestMapping("/loginPage")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/temp/login.html");
		return mv;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession httpSession, HttpServletResponse response) throws Exception {
		httpSession.invalidate();
		
		response.sendRedirect("/");
	}
}
