package com.ezen.doran.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	/* DI */
	@Autowired
	private AdminService adminService; 
	
	/* ======================= AdminNotice ======================= */
	/* 공지사항 리스트 */
	@RequestMapping("getAdNoticeList")
	public ModelAndView getAdNoticeList(HttpSession session, @RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			mv.addObject("msg", "로그인 해주세요");
			mv.addObject("url", "/temp/loginPage");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		System.out.println(loginUser.getUserRole());
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			mv.addObject("msg", "관리자 계정으로 접속해주세요");
			mv.addObject("url", "/");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		System.out.println("loginUser :" + loginUser);
		mv.addObject("loginUser", loginUser);
		

		int adTotalListCnt = adminService.getAdNoticeTotalCnt(searchKeyword);
		if (page == 0) {
			page = 1;
		} else if (page > adTotalListCnt) {
			page = adTotalListCnt;
		}
		
		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(adTotalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);
		
		List<NoticeDTO> getAdNoticeList = adminService.getAdNoticeList(pagination);

		System.out.println("공지 : " + getAdNoticeList.get(0).toString());
		
		mv.addObject("getAdNoticeList", getAdNoticeList);
		mv.addObject("pagination", pagination);
		mv.setViewName("admin/getAdNoticeList.html");

		return mv;
	}
	
	/* 공지사항 등록 & 수정 */
	@GetMapping("/insertAdNotice")
	public String insertAdNotice(
			/*
			 * @PostMapping에서 @RequestParam을 써서 받을 수 있는 것은 HTML Form 태그에서 method 가
			 * POST 전송일 때는 @RequestParam으로 값을 받을 수 있다. 그 이외의 값은 모두 Http body의
			 * 메시지로 받는다.
			 * @ReuqestParam 어노테이션은 HttpServletRequest 객체와 같은 역할을 한다.
			 * @RequestParam("가져올 데이터의 이름") [데이터타입] [가져온데이터를 담을 변수]
			 */
			HttpSession session,
			@RequestParam(value = "noticeNo", required = false) Integer noticeNo, 
			Model model) {
			/*
			 * required 옵션은 디폴트가 true이고, 써놓지 않으면 디폴트 값으로 true 가 적용.
			 * required = false => 파라미터 값을 넘겨도 되고 안넘겨도 됨
			 * 기본값 true 로 사용을 하는 경우 => 보통 줄여서 @RequestParam("num")
			 */
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("msg", "로그인 해주세요");
			model.addAttribute("url", "/temp/loginPage");
			
			return "admin/messageAlert";
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			model.addAttribute("msg", "관리자 계정으로 로그인 해주세요");
			model.addAttribute("url", "/");
			
			return "admin/messageAlert";
		}
		
		model.addAttribute("loginUser", loginUser);
		
		/*
		* noticeNo 이 null 인지 비교를 할 때 주의사항
		* Primitive Type(원시 타입) 인 int는 null 일 수 없음. null 이 필요한 경우 Integer 사용. 또는 0을 사용.
		* if(num != 0){...
		*/
		if(noticeNo != null) {
			System.out.println("리스트에서 받아온 공지 번호 :" + noticeNo);
			// null 이 아니라는 것은 파라미터 값으로 num 값이 넘어왔다는 것이므로 "수정" 처리
			// 따라서, 여기에다 수정에 대한 처리코드를 작성.
			
			// 수정 처리
			// 먼저, 넘어온 noticeNo 값에 대한 회원 정보를 데이터베이스에서 가져오고 -> 해당 회원 정보를 Form 페이지로 전달
			NoticeDTO m1 = adminService.getNoticeOne(noticeNo);
			
			// DB에서 가져온 회원 정보가 없을 경우 => 즉, m1 객체가 null 인 경우.
			if(m1 == null) {
				// 특정 페이지(Error Message page)로 데이터 값들(Model 을 사용)을 보내서 출력
				model.addAttribute("msg", "공지사항 정보가 없습니다. 공지사항 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdNoticeList");
				
				return "admin/messageAlert";  // messageAlert.html
				
			}
			
			// 콜솔에 출력테스트
			System.out.println("공지 번호 DB :" + m1.toString());
			
			// Form 페이지로 m1 객체를 전달 -> 모델(model) 이용
			model.addAttribute("noticeDTO", m1);
			model.addAttribute("formTitle", "Modification");
			model.addAttribute("noticeNo", noticeNo);
			
		}else {
			System.out.println("null 입니다.");
			// null 이라는 것은 파라미터 값으로 num 값이 넘어온게 없다는 것이므로 "입력" 처리
			// 따라서, 여기에다 등록에 대한 처리코드를 작성.
			// 폼페이지에서 받아야 하는 오프젝트 값을 넘겨주지 않기 때문에 에러발생. 빈 객체를 만들어서 전달해줘야 함
			
			// 등록 처리(새 공지사항)
			model.addAttribute("noticeDTO", new NoticeDTO());
			model.addAttribute("formTitle", "Registration");
		}
		
		return "admin/insertAdNotice";  // admin/getAdNotice.html
	}
	
	/* 공지사항 등록 */
	@PostMapping("/AdNoticeInsert")
	public String AdNoticeInsert(NoticeDTO m1, Model model) {
		
		try {
			// 등록처리
			System.out.println(m1.toString());
			
			adminService.adNoticeInsert(m1);
			
			// 등록 안내 메시지 출력
			model.addAttribute("msg", "공지사항이 등록되었습니다. 공지사항 목록으로 이동합니다.");
			model.addAttribute("url", "/admin/getAdNoticeList");
			
			return "admin/messageAlert";  // messageAlert.html
			
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		
		// redirect 리턴 : 다시 한번 해당 URL 로 HTTP 요청을 넣는 형태
		return "redirect:/";
	}
	
	
	/* 공지사항 수정 */
	@PostMapping("/AdNoticeUpdate")
	public String adUpdateNotice(NoticeDTO m1, HttpServletRequest request, Model model) {
		// 넘어오는 noticeNo 값 받아서 정수형으로 형 변환(Casting) => getParameter() 반환이 String 이므로.
		String noticeNoCs = request.getParameter("noticeNo");
		int noticeNo = Integer.parseInt(noticeNoCs);
		
		try {
			// 수정처리
			System.out.println(m1.toString());
			
			adminService.adUpdateNotice(m1);
			
			// 안내 메시지 및 URL 정보를 전달 => messageAlert.html
			model.addAttribute("msg", "공지사항이 수정되었습니다.");
			model.addAttribute("url", "/admin/insertAdNotice?noticeNo=" + noticeNo);
			
			return "admin/messageAlert";  // messageAlert.html
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		
		// redirect 리턴 : 다시 한번 해당 URL 로 HTTP 요청을 넣는 형태
		return "redirect:/admin/insertAdNotice?noticeNo=" + noticeNo;

	}
	
	/*
	공지사항 삭제
	1. Controller 삭제 구현. (삭제 요청에 대한 매핑 처리, noticeNo 변수 처리, 응답 메시지 처리 및 이동 url 전달 처리 등등..)
	2. 삭제 시 noticeNo 값이 null 인지 아닌지 체크.(null 이면 redirect)
	3. 여러 에러 상황을 대비하여  try... catch ~ 구문 사용.
	4. 삭제 처리 후 반환값을 리턴 받아서 -> 게시글 삭제 성공 시 전달할 메시지와 실패 시의 메시지를 각각 전달할 수 있도록 처리
	5. 삭제 처리 후 반환값 -> row 의 개수
	*/
	@GetMapping("/AdNoticeDelete")
	public String AdNoticeDelete(HttpSession session, @RequestParam(value="noticeNo", required = false)
								Integer noticeNo, Model model) {
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("msg", "로그인 해주세요");
			model.addAttribute("url", "/temp/loginPage");
			
			return "admin/messageAlert";
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			model.addAttribute("msg", "관리자 계정으로 로그인 해주세요");
			model.addAttribute("url", "/");
			
			return "admin/messageAlert";
		}
		
		// null 체크
		if(noticeNo == null) {
			System.out.println("null 입니다.");
			return "redirect:/admin/insertAdNotice";
		}
		System.out.println(noticeNo);
		
		// try...catch ~ 
		try {
			// 삭제에 대한 DB 처리
			// 삭제 처리 후 -> 반환값 리턴
			int delOk = adminService.adNoticeDelete(noticeNo);
			/*
			 * delOk 사용 이유
			 * 1. 값을 판별해 msg 를 출력 가능하다.
			 * 2. 성공 or 실패 url 연결을 위해 
			 */
			System.out.println("delOk = " + delOk);
			
			// 멤버 삭제 실패 처리 구현(메시지 등을 전달)
			if(delOk != 1) {
				System.out.println("삭제 실패 = " + delOk);
				//return "redirect:/member/memberList";  
				
				// 모델로 전달해서 메시지 출력 후 이동시키는 방법
				model.addAttribute("msg", "공지사항 삭제가 실패하였습니다. 공지사항 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdNoticeList");
				
			}else {
				System.out.println("삭제 성공 = " + delOk);
				
				// 삭제 성공시 -> 안내 메시지 및 이동 url 정보를 전달 -> messageAlert.html
				model.addAttribute("msg", "공지사항이 삭제되었습니다. 공지사항 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdNoticeList");
			}
			
		} catch (DataAccessException e) {
			// DB 처리시 문제가 있는지 확인
			
		} catch (Exception e) {
			// 시스템에 문제가 있는지 확인
			
		}
		
		return "/admin/messageAlert";  // messageAlert.html
	}

	/* ======================= AdminQuestionAnswer ======================= */
	/* 리스트 */
	@RequestMapping("getAdQnaList")
	public ModelAndView getAdQnaList(HttpSession session, @RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			mv.addObject("msg", "로그인 해주세요");
			mv.addObject("url", "/temp/loginPage");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			mv.addObject("msg", "관리자 계정으로 접속해주세요");
			mv.addObject("url", "/");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		mv.addObject("loginUser", loginUser);

		int adTotalListCnt = adminService.getAdQnaTotalCnt(searchKeyword);
		if (page == 0) {
			page = 1;
		} else if (page > adTotalListCnt) {
			page = adTotalListCnt;
		}
		
		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(adTotalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);
		
		List<QuestionDTO> getAdQnaList = adminService.getAdQnaList(pagination);

		System.out.println("1:1 :" + getAdQnaList.get(0).toString());
		
		mv.addObject("getAdQnaList", getAdQnaList);
		mv.addObject("pagination", pagination);
		mv.setViewName("admin/getAdQnaList.html");

		return mv;
	}

	/* 1:1 답변 등록 & 수정 */
	@GetMapping("/insertAdQna")
	public String insertAdQna(HttpSession session, Model model,
								@RequestParam(value = "qNo", required = false) Integer qNo) {

		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("msg", "로그인 해주세요");
			model.addAttribute("url", "/temp/loginPage");
			
			return "admin/messageAlert";
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			model.addAttribute("msg", "관리자 계정으로 로그인 해주세요");
			model.addAttribute("url", "/");
			
			return "admin/messageAlert";
		}
		
		model.addAttribute("loginUser", loginUser);
	
		QuestionDTO question = adminService.getQuestionOne(qNo);
		AnswerDTO answer = adminService.getAnswerOne(qNo);
		
		if(qNo != null && answer.getANo() == 0){
			System.out.println("qNo :"+ qNo);

			if(question == null) {

				model.addAttribute("msg", "1:1 문의 정보가 없습니다. 1:1문의 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdQnaList");
				
				return "admin/messageAlert";
				
			}
			
			// 콜솔에 출력테스트
			System.out.println("question:" + question.toString());
			System.out.println("answer" + answer.toString());
			
			model.addAttribute("question", question);
			model.addAttribute("answer", answer);
			model.addAttribute("formTitle", "Registration");
			model.addAttribute("qNo", qNo);
			
		}else {
			System.out.println("aNo" + answer.getANo());

			model.addAttribute("question", question);
			model.addAttribute("answer", answer);
			model.addAttribute("formTitle", "Modification");
		}
		
		return "admin/insertAdQna";
	}
	
	/* 1:1 문의 답변 등록 */
	@PostMapping("/AdAnswerInsert")
	public String AdAnswerInsert(AnswerDTO m1, HttpServletRequest request, Model model) {
		
		try {
			System.out.println(m1.toString());
			
			adminService.adAnswerInsert(m1);
			
			model.addAttribute("msg", "1:1문의 답변이 등록되었습니다. 1:1문의 목록으로 이동합니다.");
			model.addAttribute("url", "/admin/getAdQnaList");
			
			return "admin/messageAlert";
			
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		
		return "redirect:/";
	}
	
	/* 1:1 문의 수정 */
	@PostMapping("/AdAnswerUpdate")
	public String AdAnswerUpdate(AnswerDTO m1, HttpServletRequest request, Model model) {

		String qNoCs = request.getParameter("qNo");
		int qNo = Integer.parseInt(qNoCs);
		
		try {
			// 수정처리
			System.out.println("AnswerDTO :" + m1.toString());
			
			adminService.adAnswerUpdate(m1);
			
			model.addAttribute("msg", "1:1 문의 답변이 수정되었습니다.");
			model.addAttribute("url", "/admin/insertAdQna?qNo=" + qNo);
			
			return "admin/messageAlert";  // messageAlert.html
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		return "redirect:/admin/insertAdQnA?qNo=" + qNo;

	}
	/* ======================= AdminReport ======================= */
	/* 리스트 */ 
	@RequestMapping("getAdReportList")
	public ModelAndView getAdReportList(HttpSession session, @RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			mv.addObject("msg", "로그인 해주세요");
			mv.addObject("url", "/temp/loginPage");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			mv.addObject("msg", "관리자 계정으로 접속해주세요");
			mv.addObject("url", "/");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		mv.addObject("loginUser", loginUser);

		int adTotalListCnt = adminService.getAdReportTotalCnt(searchKeyword);
		if (page == 0) {
			page = 1;
		} else if (page > adTotalListCnt) {
			page = adTotalListCnt;
		}
		
		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(adTotalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);
		
		List<RepDTO> getAdReportList = adminService.getAdReportList(pagination);

		System.out.println("신고 :" + getAdReportList.get(0).toString());
		
		mv.addObject("getAdReportList", getAdReportList);
		mv.addObject("pagination", pagination);
		mv.setViewName("admin/getAdReportList.html");

		return mv;
	}
	
	/* 신고 확인 */
	@GetMapping("/insertAdReport")
	public String insertAdReport(HttpSession session, Model model,
								@RequestParam(value = "repNo", required = false) Integer repNo) {

		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("msg", "로그인 해주세요");
			model.addAttribute("url", "/temp/loginPage");
			
			return "admin/messageAlert";
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			model.addAttribute("msg", "관리자 계정으로 로그인 해주세요");
			model.addAttribute("url", "/");
			
			return "admin/messageAlert";
		}
		
		model.addAttribute("loginUser", loginUser);
	
		RepDTO report = adminService.getReportOne(repNo);
		
		if(repNo != null){
			System.out.println("repNo :"+ repNo);

			if(report == null) {

				model.addAttribute("msg", "신고 정보가 없습니다. 신고 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdReportList");
				
				return "admin/messageAlert";
				
			}
			
			// 콜솔에 출력테스트
			System.out.println("report:" + report.toString());
			
			model.addAttribute("report", report);
			model.addAttribute("formTitle", "Registration");
			model.addAttribute("repNo", repNo);
			
		}else {

			model.addAttribute("formTitle", "Modification");
		}
		
		return "admin/insertAdReport";
	}
	
	/* 신고 확인 처리 */
	@PostMapping("/AdReportUpdate")
	public String AdReportUpdate(RepDTO m1, HttpServletRequest request, Model model) {

		String repNoCs = request.getParameter("repNo");
		int repNo = Integer.parseInt(repNoCs);
		
		try {
			// 수정처리
			System.out.println("RepDTO :" + m1.toString());
			
			adminService.adReportUpdate(m1);
			
			model.addAttribute("msg", "신고 처리가 완료되었습니다.");
			model.addAttribute("url", "/admin/insertAdReport?repNo=" + repNo);
			
			return "admin/messageAlert";  // messageAlert.html
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		return "redirect:/admin/insertAdReport?repNo=" + repNo;

	}
	/* ======================= AdminUserRole ======================= */
	/* 리스트 */
	@RequestMapping("getAdUserList")
	public ModelAndView getAdUserList(HttpSession session, @RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		
		ModelAndView mv = new ModelAndView();
		
		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			mv.addObject("msg", "로그인 해주세요");
			mv.addObject("url", "/temp/loginPage");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			mv.addObject("msg", "관리자 계정으로 접속해주세요");
			mv.addObject("url", "/");
			mv.setViewName("admin/messageAlert.html");

			return mv;
		}
		
		mv.addObject("loginUser", loginUser);

		int adTotalListCnt = adminService.getAdUserTotalCnt(searchKeyword);
		if (page == 0) {
			page = 1;
		} else if (page > adTotalListCnt) {
			page = adTotalListCnt;
		}
		
		// 생성인자로 총 게시글 수, 현재 페이지 전달
		Pagination pagination = new Pagination(adTotalListCnt, page);
		pagination.setSearchKeyword(searchKeyword);
		
		List<UserDTO> getAdUserList = adminService.getAdUserList(pagination);

		System.out.println("유저 :" + getAdUserList.get(0).toString());
		
		mv.addObject("getAdUserList", getAdUserList);
		mv.addObject("pagination", pagination);
		mv.setViewName("admin/getAdUserList.html");

		return mv;
	}
	
	/* 유저 확인 */
	@GetMapping("/insertAdUser")
	public String insertAdUser(HttpSession session, Model model,
								@RequestParam(value = "userNo", required = false) Integer userNo) {

		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("msg", "로그인 해주세요");
			model.addAttribute("url", "/temp/loginPage");
			
			return "admin/messageAlert";
		}
		
		if(!loginUser.getUserRole().equals("ADMIN")) {
			model.addAttribute("msg", "관리자 계정으로 로그인 해주세요");
			model.addAttribute("url", "/");
			
			return "admin/messageAlert";
		}
		
		model.addAttribute("loginUser", loginUser);
	
		UserDTO adUser = adminService.getUserOne(userNo);
		
		if(userNo != null){
			System.out.println("adUser :"+ adUser);

			if(adUser == null) {

				model.addAttribute("msg", "유저 정보가 없습니다. 유저 목록으로 이동합니다.");
				model.addAttribute("url", "/admin/getAdUserList");
				
				return "admin/messageAlert";
				
			}
			
			// 콜솔에 출력테스트
			System.out.println("userNo:" + userNo.toString());
			
			model.addAttribute("adUser", adUser);
			model.addAttribute("formTitle", "Registration");
			model.addAttribute("userNo", userNo);
			
		}else {

			model.addAttribute("formTitle", "Modification");
		}
		
		return "admin/insertAdUser";
	}
	
	/* 유저 ROLE 변경 */
	@PostMapping("/AdUserUpdate")
	public String AdUserUpdate(UserDTO m1, HttpServletRequest request, Model model) {

		String userNoCs = request.getParameter("userNo");
		int userNo = Integer.parseInt(userNoCs);
		
		try {
			// 수정처리
			System.out.println("UserDTO :" + m1.toString());
			
			adminService.adUserUpdate(m1);
			
			model.addAttribute("msg", "회원 상태가 변경되었습니다.");
			model.addAttribute("url", "/admin/insertAdUser?userNo=" + userNo);
			
			return "admin/messageAlert";  // messageAlert.html
		} catch (Exception e) {
			//err
			System.out.println(e);
		}
		return "redirect:/admin/insertAdUser?userNo=" + userNo;

	}
}