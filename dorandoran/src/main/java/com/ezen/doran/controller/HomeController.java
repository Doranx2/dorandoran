package com.ezen.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.service.home.HomeService;

@RestController
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		
		
		mv.addObject("notice", homeService.selectHomeNoticeList());
		mv.addObject("play", homeService.selectHomePlayList());
		mv.addObject("help", homeService.selectHomeHelpList());
		mv.addObject("join", homeService.selectHomeJoinList());
		mv.setViewName("index.html");
		
		return mv;
	}
}
