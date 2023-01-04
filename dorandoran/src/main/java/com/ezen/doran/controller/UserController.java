/*
 * package com.ezen.doran.controller;
 * 
 * import java.time.LocalDateTime; import java.util.HashMap; import
 * java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.ezen.doran.dto.ResponseDTO; import com.ezen.doran.dto.UserDTO;
 * import com.ezen.doran.entity.User; import com.ezen.doran.service.UserService;
 * 
 * @RestController
 * 
 * @RequestMapping("/user") public class UserController {
 * 
 * @Autowired private UserService userService;
 * 
 * @Autowired private PasswordEncoder passwordEncoder;
 * 
 * //회원가입
 * 
 * @GetMapping("/join") public ModelAndView joinView() { ModelAndView mv = new
 * ModelAndView(); mv.setViewName("/user/join.html"); return mv;
 * 
 * }
 * 
 * @PostMapping("/join") public ResponseEntity<?> join(UserDTO userDTO){
 * 
 * ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();
 * Map<String, String> returnMap = new HashMap<String, String>(); try { User
 * user = User.builder() .userNo(userDTO.getUserNo())
 * .userId(userDTO.getUserId())
 * .userPw(passwordEncoder.encode(userDTO.getUserPw()))
 * .userNm(userDTO.getUserNm()) .userEmail(userDTO.getUserEmail())
 * .userAge(userDTO.getUserAge()) .userGen(userDTO.getUserGen())
 * .userLoc(userDTO.getUserLoc()) .userNick(userDTO.getUserNick())
 * .inputDtm(LocalDateTime.now()) .build();
 * 
 * userService.join(user);
 * 
 * returnMap.put("joinMsg", "joinSuccess");
 * 
 * responseDTO.setItem(returnMap);
 * 
 * return ResponseEntity.ok().body(responseDTO); } catch(Exception e) {
 * returnMap.put("joinMsg", "joinFail");
 * responseDTO.setErrorMessage(e.getMessage()); responseDTO.setItem(returnMap);
 * 
 * return ResponseEntity.badRequest().body(responseDTO); } }
 * 
 * 
 * // 중복 아이디 체크
 * 
 * @PostMapping("/idCheck") public ResponseEntity<?> idCheck(UserDTO userDTO){
 * ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();
 * Map<String, String> returnMap = new HashMap<String, String>();
 * 
 * try { User user = User.builder() .userId(userDTO.getUserid()) .build();
 * 
 * User checkedUser = userService.idCheck(user);
 * 
 * if(checkedUser != null) { returnMap.put("msg","duplicatedId"); }else {
 * returnMap.put("msg","idOk"); } responseDTO.setItem(returnMap);
 * 
 * return ResponseEntity.ok().body(responseDTO); } catch(Exception e) {
 * responseDTO.setErrorMessage(e.getMessage()); return
 * ResponseEntity.badRequest().body(responseDTO); } }
 * 
 * // 로그인
 * 
 * @GetMapping("/login") public ModelAndView loginView() { ModelAndView mv = new
 * ModelAndView();
 * 
 * mv.setViewName("/user/login.html"); return mv; }
 * 
 * @PostMapping("/login") public ResponseEntity<?> }
 */