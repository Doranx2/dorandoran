package com.ezen.doran.controller;
  
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.ResponseDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.entity.User;
import com.ezen.doran.service.UserService;
  
  @RestController
  
  @RequestMapping("/user") 
  public class UserController {
  
  @Autowired 
  private UserService userService;
	
	  //회원가입
	  @GetMapping("/join") 
	  public ModelAndView joinView() { 
		  ModelAndView mv = new ModelAndView(); 
		  mv.setViewName("/user/join.html"); 
		  return mv;
	  }
	  
	  @PostMapping("/join") 
	  public ModelAndView join(UserDTO userDTO){
		  ModelAndView mv = new ModelAndView();
		  
		  //회원가입 처리
		  //DTO로 받아온 사용자 입력 값들을 Entity로 변환
		  User user = User.builder()
				  		  .userId(userDTO.getUserId())
				  		  .userAge(userDTO.getUserAge())
				  		  .userEmail(userDTO.getUserEmail())
				  		  .userGen(userDTO.getUserGen())
				  		  .userLoc(userDTO.getUserLoc())
				  		  .userPw(userDTO.getUserPw())
				  		  .userNick(userDTO.getUserNick())
				  		  .userNm(userDTO.getUserNm())
				  		  .userTel(userDTO.getUserTel())
				  		  .build();
		  
		  //변환된 Entity 객체로 회원가입 처리
		  userService.join(user);
		  
		  mv.setViewName("user/login.html");
		  
		  return mv; 	  
	  }
	  
	  //아이디 중복체크
	  @PostMapping("idCheck")
	  public ResponseEntity<?> idCheck(@RequestParam("userId") String userId) {
		  ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
		  
		  try {
			  User user = User.builder()
					  		  .userId(userId)
					  		  .build();
			  
			  User chkUser = userService.idCheck(user);
			  
			  Map<String, String> returnMap = new HashMap<String, String>();
			  
			  if(chkUser != null) {
				  returnMap.put("msg", "duplicatedId");
			  } else {
				  returnMap.put("msg", "idOk");
			  }
			  
			  response.setItem(returnMap);
			  
			  return ResponseEntity.ok().body(response);
			  } catch(Exception e) {
			  response.setErrorMessage(e.getMessage());
			  return ResponseEntity.badRequest().body(response);
		  }
	  }
	

	 
	  //로그인
	  @GetMapping("/login") 
	  public ModelAndView loginView() { 
	     ModelAndView mv = new ModelAndView(); 
	     mv.setViewName("/user/login.html"); 
	     return mv;
     }
  
	  //아이디 찾기
	  //화면단
	  @GetMapping("/findIdCheck") 
	  public ModelAndView findIdCheckView() { 
	     ModelAndView mv = new ModelAndView(); 
	     mv.setViewName("/user/findIdCheck.html"); 
	     return mv;
  	}
	  //체크
	  /*
	  @PostMapping("/idfCheck")
	  public ResponseEntity<?>idfCheck(@RequestParam("userNm")String userNm, @RequestParam("userEmail") String userEmail){
		  ResponseDTO<Map<String, String>> response = new ResponseDTO<>();
		  
		  try {
			  User user = User.builder()
			  		  			.userNm(userNm)
			  		  			.userEmail(userEmail)
		  		  				.build();
			  User findIdChkUser = userService.idfCheck(user);
			  
			  Map<String, String> returnMap = new HashMap<String, String>();
	
		  }
		  
		  
	  }*/
	
  
  

  
  
  
  
  //아이디 찾기 성공
  @RequestMapping("/findId") 
  public ModelAndView findIdView() { 
     ModelAndView mv = new ModelAndView(); 
     mv.setViewName("/user/findId.html"); 
     return mv;
  	}
  
  
  
  
  //비밀번호 찾기
  @GetMapping("/findPwCheck") 
  public ModelAndView findPwCheckView() { 
     ModelAndView mv = new ModelAndView(); 
     mv.setViewName("/user/findPwCheck.html"); 
     return mv;
  	}
  @PostMapping("/findPwCheck")
  
  
  
  
  
	/*
	 * @PostMapping("/findPwCheck") public ModelAndView findPwCheck(User DTO
	 * userDTO) { ModelAndView mv = new ModelAndView(); }
	 */
  
  //비밀번호 찾기 성공
  @RequestMapping("/findPw") 
  public ModelAndView findPwView() { 
     ModelAndView mv = new ModelAndView(); 
     mv.setViewName("/user/findPw.html"); 
     return mv;
  	}
  }
  
  
 