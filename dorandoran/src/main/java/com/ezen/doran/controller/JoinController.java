package com.ezen.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.service.join.JoinService;
import com.ezen.doran.service.re.ReService;

@RestController
@RequestMapping("/join")
public class JoinController {

	@Autowired
	JoinService joinService;
	
	@Autowired
	ReService reService;
	
	@RequestMapping("/selectJoinList")
	public ModelAndView selectJoinList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/join/selectJoinList.html");
		
		return mv;
	}
	
	@RequestMapping("/insertJoinPage")
	public ModelAndView insertJoinPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/join/insertJoin.html");
		
		return mv;
	}
}
