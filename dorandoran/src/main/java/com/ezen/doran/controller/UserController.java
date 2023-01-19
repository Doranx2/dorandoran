package com.ezen.doran.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.ResponseDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.entity.User;
import com.ezen.doran.service.user.UserService;

@RestController()

@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

	// 회원가입
	@GetMapping("/join")
	public ModelAndView joinView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/join.html");
		return mv;
	}

	@PostMapping("/join")
	public ModelAndView join(UserDTO userDTO) {
		ModelAndView mv = new ModelAndView();

		// 회원가입 처리
		// DTO로 받아온 사용자 입력 값들을 Entity로 변환
		User user = User.builder()
				.userId(userDTO.getUserId())
				.userAge(userDTO.getUserAge())
				.userEmail(userDTO.getUserEmail())
				.userGen(userDTO.getUserGen()).userLoc(userDTO.getUserLoc())
                .userPw(passwordEncoder.encode(userDTO.getUserPw()))
//				.userPw(userDTO.getUserPw())
				.userNick(userDTO.getUserNick()).userNm(userDTO.getUserNm())
				.userTel(userDTO.getUserTel()).build();

		// 변환된 Entity 객체로 회원가입 처리
		userService.join(user);

		mv.setViewName("user/login.html");

		return mv;
	}

	// 아이디 중복체크
	@PostMapping("idCheck")
	public ResponseEntity<?> idCheck(@RequestParam("userId") String userId) {
		ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

		try {
			User user = User.builder().userId(userId).build();

			User chkUser = userService.idCheck(user);

			Map<String, String> returnMap = new HashMap<String, String>();

			if (chkUser != null) {
				returnMap.put("msg", "duplicatedId");
			} else {
				returnMap.put("msg", "idOk");
			}

			response.setItem(returnMap);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// 닉네임 중복체크
	@PostMapping("nickCheck")
	public ResponseEntity<?> nickCheck(@RequestParam("userNick") String userNick) {
		ResponseDTO<Map<String, String>> response = new ResponseDTO<>();

		try {
			User user = User.builder().userNick(userNick).build();

			User chkUser = userService.nickCheck(user);

			Map<String, String> returnMap = new HashMap<String, String>();

			if (chkUser != null) {
				returnMap.put("msg", "duplicatedNick");
			} else {
				returnMap.put("msg", "nickOk");
			}

			response.setItem(returnMap);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// 로그인
	@GetMapping("/login")
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/login.html");
		return mv;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(UserDTO userDTO, HttpSession session) {
		System.out.println("id===============================================" + userDTO.getUserId());
		System.out.println("pw===============================================" + userDTO.getUserPw());
		ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			User user = User.builder().userId(userDTO.getUserId()).userPw(userDTO.getUserPw()).build();
			User checkedUser = userService.idCheck(user);
			System.out.println(checkedUser);
			if (checkedUser == null) {
				returnMap.put("msg", "idFail");
			} else {
				if (!passwordEncoder.matches(userDTO.getUserPw(), checkedUser.getUserPw())) {
					returnMap.put("msg", "pwFail");
				} else {
					UserDTO loginUser = UserDTO.builder().userId(checkedUser.getUserId())
							.userAge(checkedUser.getUserAge()).userEmail(checkedUser.getUserEmail())
							.userGen(checkedUser.getUserGen()).userLoc(checkedUser.getUserLoc())
							.userPw(checkedUser.getUserPw()).userNick(checkedUser.getUserNick())
							.userNm(checkedUser.getUserNm()).userTel(checkedUser.getUserTel())
							.userNo(checkedUser.getUserNo()).userRole(checkedUser.getUserRole())
							.inputDtm(checkedUser.getInputDtm())

							.build();
					session.setAttribute("loginUser", loginUser);

					System.out.println("[session : loginUser ] ==> " + session.getAttribute("loginUser"));

					returnMap.put("msg", "loginSuccess");
				}
			}
			responseDTO.setItem(returnMap);
			return ResponseEntity.ok().body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	@RequestMapping("/logout")
	public void logout(HttpSession httpSession, HttpServletResponse response) throws Exception {
		httpSession.invalidate();

		response.sendRedirect("/");
	}

	// 아이디 찾기 화면단
	@GetMapping("/findIdCheck")
	public ModelAndView findIdCheckView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/findIdCheck.html");
		return mv;
	}

	// 아이디 찾기
	@PostMapping("/idfCheck")
	public ResponseEntity<?> idfCheck(UserDTO userDTO, HttpSession session) {
		ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			User user = User.builder().userNm(userDTO.getUserNm()).userEmail(userDTO.getUserEmail()).build();
			User findUser = userService.idfCheck(user);
			System.out.println(findUser);
			if (findUser == null) {
				returnMap.put("msg", "nameFail");
			} else {
				if (!findUser.getUserEmail().equals(userDTO.getUserEmail())) {
					returnMap.put("msg", "emailFail");
				} else {
					/*
					 * UserDTO idUser = UserDTO.builder() .userId(findUser.getUserId())
					 * .userAge(findUser.getUserAge()) .userEmail(findUser.getUserEmail())
					 * .userGen(findUser.getUserGen()) .userLoc(findUser.getUserLoc())
					 * .userPw(findUser.getUserPw()) .userNick(findUser.getUserNick())
					 * .userNm(findUser.getUserNm()) .userTel(findUser.getUserTel()) .build();
					 * session.setAttribute("findUser", findUser);
					 */
					returnMap.put("msg", "findSuccess");
					returnMap.put("findId", findUser.getUserId());

				}
			}
			responseDTO.setItem(returnMap);
			return ResponseEntity.ok().body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(responseDTO);

		}
	}

	// 아이디 찾기 성공
	@PostMapping("/findId")
	public ModelAndView findIdView(UserDTO userDTO) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/findId.html");
		mv.addObject("userId", userDTO.getUserId());
		return mv;
	}

	// 비밀번호 찾기 화면단
	@GetMapping("/findPwCheck")
	public ModelAndView findPwCheckView() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/findPwCheck.html");
		return mv;
	}

	// 비밀번호 찾기
	// 아이디 + 이메일 입력하여 이메일로 임시 비밀번호 전송
	@PostMapping("/pwfCheck")
	public ResponseEntity<?> pwfCheck(UserDTO userDTO, HttpSession session) {
		ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>();
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			User user = User.builder().userId(userDTO.getUserId()).userEmail(userDTO.getUserEmail()).build();
			User fndPwUser = userService.pwfCheck(user);
			System.out.println(fndPwUser);
			if (fndPwUser == null) {
				returnMap.put("msg", "idFail");
			} else {
				if (!fndPwUser.getUserEmail().equals(userDTO.getUserEmail())) {
					returnMap.put("msg", "emailFail");
				} else {
					/*
					 * UserDTO pwUser = userDTO.builder() .userId(fndPwUser.getUserId())
					 * .userAge(fndPwUser.getUserAge()) .userEmail(fndPwUser.getUserEmail())
					 * .userGen(fndPwUser.getUserGen()) .userLoc(fndPwUser.getUserLoc())
					 * .userPw(fndPwUser.getUserPw()) .userNick(fndPwUser.getUserNick())
					 * .userNm(fndPwUser.getUserNm()) .userTel(fndPwUser.getUserTel()) .build();
					 * session.setAttribute("pwUser", pwUser);
					 */
					// 입력한 id와 email이 정확할 때 임시비밀번호 생성 및 저장 그리고 메일로 발송
					userService.updateTempPw(fndPwUser);

					returnMap.put("msg", "findPwSuccess");
					// returnMap.put("findPw",fndPwUser.getUserPw());
				}
			}
			responseDTO.setItem(returnMap);
			return ResponseEntity.ok().body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}

	// 비밀번호 찾기 성공
	@PostMapping("/findPw")
	public ModelAndView findPwView(UserDTO userDTO) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/findPw.html");
		mv.addObject("userPw", userDTO.getUserPw());
		mv.addObject("userEmail", userDTO.getUserEmail());
		return mv;
	}
	
	// 회원탈퇴
	@PostMapping("/deleteUser")
	public void deleteUser(@RequestParam("userNo") int userNo) {
		userService.deleteUser(userNo);
	}
}
