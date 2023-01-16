package com.ezen.doran.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
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
	
	@RequestMapping("/updateUserPage")
	public ModelAndView updateUser() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/updateUserPage.html");
		return mv;
	}
	
//	@RequestMapping("/playListPage")
	public ModelAndView myPlayList() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/userPage/playListPage.html");
		return mv;
	}
	
//	@RequestMapping("/writingListPage")
//	public ModelAndView myWritingList() {
//		ModelAndView mv = new ModelAndView();
//		
//		mv.setViewName("/userPage/writingListPage.html");
//		return mv;
//	}
	
	// updateUser(UserDTO)
	@PostMapping("/updateUser")
	public void updateUser(UserDTO UserDTO, HttpServletResponse response) throws Exception {
		UserPageService.updateUser(UserDTO);
		
		response.sendRedirect("/userPage/updateUserPage");
	}
	
	
//	 selectMyJoinList(int)
//	@RequestMapping("/playListPage")
//	public ModelAndView selectMyPlayList(@RequestParam("userNo") int userNo) {
//		ModelAndView mv = new ModelAndView();
//		
//		List<JoinDTO> selectMyJoinList = UserPageService.selectMyJoinList(userNo);
//		
//		mv.addObject("myJoin", selectMyJoinList);
//		mv.setViewName("/userPage/marketListPage.html");
//		
//		return mv;
//	}
	
	
	// selectMyMarketList(int, String)
	@RequestMapping("/marketListPage")
	public ModelAndView selectMyMarketList(@RequestParam("userNo") int userNo) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("userNo:"+userNo);
		List<MarketDTO> selectMyMarketList = UserPageService.selectMyMarketList(userNo);
		
		mv.addObject("myMarket", selectMyMarketList);
		mv.setViewName("/userPage/marketListPage.html");
		
		
		return mv;
	}
	
	// selectMyQuestionList(int)
	// selectMyRepList(int)
	@RequestMapping("/questionListPage")
	public ModelAndView selectMyQuestionList(@RequestParam("userNo") int userNo) {
		ModelAndView mv = new ModelAndView();
		
		List<QuestionDTO> selectMyQuestionList = UserPageService.selectMyQuestionList(userNo);
		List<RepDTO> selectMyRepList = UserPageService.selectMyRepList(userNo);
		
		
		mv.addObject("myQuestion", selectMyQuestionList);
		mv.addObject("myRep", selectMyRepList);
		mv.setViewName("/userPage/questionListPage.html");
		
		System.out.println(selectMyQuestionList);
		
		return mv;
		
		
	}
	
	
	// selectMyWritingList(int)
	@RequestMapping("/writingListPage")
	
	
	

}
