package com.ezen.doran.controller;
  
  import java.time.LocalDateTime; 
  import java.util.HashMap; 
  import java.util.Map;
  
  import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.http.ResponseEntity; 
  import org.springframework.web.bind.annotation.GetMapping; 
  import org.springframework.web.bind.annotation.PostMapping; 
  import org.springframework.web.bind.annotation.RequestMapping; 
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
  
  @Autowired 
/*  private PasswordEncoder passwordEncoder;*/  
  
  //회원가입
  
  @RequestMapping("/join") 
  public ModelAndView joinView() { 
     ModelAndView mv = new ModelAndView(); 
     mv.setViewName("/user/join.html"); 
     return mv;
   
  }
  
  
  //로그인
  
  @RequestMapping("/login") 
  public ModelAndView loginView() { 
     ModelAndView mv = new ModelAndView(); 
     mv.setViewName("/user/login.html"); 
     return mv;
  	}
  }
 