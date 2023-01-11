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

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.UserDTO;
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
	
//	@RequestMapping("/marketListPage")
//	public ModelAndView myMarketList() {
//		ModelAndView mv = new ModelAndView();
//		
//		mv.setViewName("/userPage/marketListPage.html");
//		return mv;
//	}
	
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
	
	
	// updateUser(UserDTO)
	@PostMapping("/updateUser")
	public void updateUser(UserDTO UserDTO, HttpServletResponse response) throws Exception {
		UserPageService.updateUser(UserDTO);
		
		response.sendRedirect("/userPage/updateUserPage");
	}
	
	
	// selectMyJoinList(int)
	@RequestMapping("/selectMyJoinList")
	public String selectMyPlayList(Model model) {
		
		List<PlayDTO> selectMyPlayList = UserPageService.selectMyPlayList();
		
		model.addAttribute("selectMyPlayList" , selectMyPlayList);
		
		return "/userPage/playListPage.html";
	}
	
	
	// selectMyMarketList(int, String)
	@RequestMapping("/marketListPage")
	public ModelAndView selectMyMarketList(@RequestParam("userNo") int userNo) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("userNo:"+userNo);
		List<MarketDTO> selectMyMarketList = UserPageService.selectMyMarketList(userNo);
		
		mv.addObject("myMarket", selectMyMarketList);
		mv.setViewName("/userPage/marketListPage.html");
		
		System.out.println(selectMyMarketList);
		
		return mv;
	}
	
	
	// selectMyHelpList(int, String)
	
	
	// selectMyShareList(int)
	
	
	// selectMyRepList(int)
	
	
	// selectMyQuestionList(int)
}