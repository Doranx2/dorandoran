package com.ezen.doran.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
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
		
		System.out.println(loginUser + "리스트");
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
		// 정보 출력 테스트(콘솔에 표시). 리스트의 첫번째값이 출력됨(no=1)
		System.out.println(getAdNoticeList.get(0).toString());
		
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
		
		model.addAttribute("loginUser", loginUser);
		
		
		/*
		* noticeNo 이 null 인지 비교를 할 때 주의사항
		* Primitive Type(원시 타입) 인 int는 null 일 수 없음. null 이 필요한 경우 Integer 사용. 또는 0을 사용.
		* if(num != 0){...
		*/
		if(noticeNo != null) {
			System.out.println(noticeNo);
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
			System.out.println(m1.toString());
			
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
	public String adUpdateNotice(
			NoticeDTO m1,
			HttpServletRequest request,
			Model model
			) {
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
	public String AdNoticeDelete(@RequestParam(value="noticeNo", required = false) Integer noticeNo, Model model) {
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
	public ModelAndView getAdQnaList(@RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "") String searchKeyword) {
		
		ModelAndView mv = new ModelAndView();

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
		// 정보 출력 테스트(콘솔에 표시). 리스트의 첫번째값이 출력됨(no=1)
		System.out.println(getAdQnaList.get(0).toString());
		
		mv.addObject("getAdQnaList", getAdQnaList);
		mv.addObject("pagination", pagination);
		mv.setViewName("admin/getAdQnaList.html");

		return mv;
	}

	@GetMapping("/question/{qNo}")
	public ModelAndView selectQuestion(@PathVariable int qNo) {
		QuestionDTO qDetail = adminService.getQnaOne(qNo);
		AnswerDTO answer = adminService.getQnaAnswerOne(qNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/questionDetail.html");
		mv.addObject("qDetail", qDetail);
		mv.addObject("answer", answer);
		return mv;
	}
	
	
//	// 1:1문의 조회 및 답변
//	@GetMapping("insertAdQnA/{qNo}")
//	public ModelAndView adUpdateAnswer(@PathVariable int qNo, Model model, HttpSession session) {
//		QuestionDTO question = adminService.getQnaOne(qNo);
//		AnswerDTO answer = adminService.getQnaAnswerOne(qNo);
//		
//		ModelAndView mv = new ModelAndView();
//		
//		System.out.println(question.toString());
//		System.out.println(answer.toString());
//				
//		UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
//		mv.addObject("loginUser", loginUser);
//		
//		mv.setViewName("/admin/insertAdQnA.html");
//		mv.addObject("question", question);
//		mv.addObject("answer", answer);
//		return mv;
//	}
//
//	/* 1:1 문의 수정 */
//	@PostMapping("/AdAnswerUpdate")
//	public String AdAnswerUpdate(AnswerDTO m1, HttpServletRequest request, Model model) {
//		String qNoCs = request.getParameter("qNo");
//		int qNo = Integer.parseInt(qNoCs);
//		
//		try {
//			System.out.println(m1.toString());
//			
//			adminService.AdAnswerUpdate(m1);
//			
//			model.addAttribute("msg", "1:1 문의 등록되었습니다.");
//			model.addAttribute("url", "/admin/insertAdQnA?qNo=" + qNo);
//			
//			return "admin/messageAlert";
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		return "redirect:/admin/insertAdQnA?qNo=" + qNo;
//	}
//	
//	@GetMapping("/AdQnaDelete")
//	public String AdQnaDelete(@RequestParam(value="qNo", required = false) Integer qNo, Model model) {
//		// null 체크
//		if(qNo == null) {
//			System.out.println("null 입니다.");
//			return "redirect:/admin/insertAdQnA";
//		}
//		System.out.println(qNo);
//		
//		try {
//			int delOk = adminService.adQnaDelete(qNo);
//			
//			System.out.println("delOk = " + delOk);
//			
//			if(delOk != 1) {
//				System.out.println("삭제 실패 = " + delOk);
//			
//				model.addAttribute("msg", "1:1 문의 삭제가 실패하였습니다. 1:1 문의 목록으로 이동합니다.");
//				model.addAttribute("url", "/admin/getAdQnaList");
//				
//			}else {
//				System.out.println("삭제 성공 = " + delOk);
//				
//				model.addAttribute("msg", "1:1 문의가 삭제되었습니다. 1:1 문의 목록으로 이동합니다.");
//				model.addAttribute("url", "/admin/getAdNoticeList");
//			}
//			
//		} catch (DataAccessException e) {
//			
//		} catch (Exception e) {
//			
//		}
//		
//		return "/admin/messageAlert";
//	}
	/* ======================= AdminReport ======================= */

}