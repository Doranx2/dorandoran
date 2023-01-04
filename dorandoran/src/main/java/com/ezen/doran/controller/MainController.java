package com.ezen.doran.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

	@RestController
	@RequestMapping("/") 
	public class MainController {

	@RequestMapping("/main") 
	public ModelAndView mainPage() { 
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("main.html"); 
		return mv; 
		}

 }
