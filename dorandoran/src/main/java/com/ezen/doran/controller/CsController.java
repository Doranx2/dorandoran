package com.ezen.doran.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.Criteria;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.PageDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.ResponseDTO;
import com.ezen.doran.service.cs.CsService;

@RestController
@RequestMapping("/cscenter")
public class CsController {
	@Autowired
	private CsService csService;

	// 공지글 목록
	@GetMapping("/noticeList")
	public ModelAndView selectNoticeList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ModelAndView mv = new ModelAndView();
		List<NoticeDTO> noticeList = csService.selectNoticeList(paramMap, cri);
		mv.addObject("noticeList", noticeList);

		if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
			mv.addObject("searchCondition", paramMap.get("searchCondition"));
		}

		if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
			mv.addObject("searchKeyword", paramMap.get("searchKeyword"));
		}

		int total = csService.getNoticeTotalCnt(paramMap);
		mv.addObject("pageDTO", new PageDTO(cri, total));

		mv.setViewName("/cscenter/noticeList.html");

		return mv;
	}

	@GetMapping("/pageNoticeList")
	public ResponseEntity<?> pageNoticeList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ResponseDTO<Map<String, Object>> response = new ResponseDTO<>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {

			List<NoticeDTO> noticeList = csService.selectNoticeList(paramMap, cri);
			returnMap.put("noticeList", noticeList);

			if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
				returnMap.put("searchCondition", paramMap.get("searchCondition"));
			}

			if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
				returnMap.put("searchKeyword", paramMap.get("searchKeyword"));
			}

			int total = csService.getNoticeTotalCnt(paramMap);
			returnMap.put("pageDTO", new PageDTO(cri, total));

			response.setItem(returnMap);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// 공지글 상세보기
	@GetMapping("/notice/{noticeNo}")
	public ModelAndView selectNotice(@PathVariable int noticeNo) {
		ModelAndView mv = new ModelAndView();
		NoticeDTO nDetail = csService.selectNotice(noticeNo);
		int nPrevNo = csService.prevNoticeNo(noticeNo);
		int nNextNo = csService.nextNoticeNo(noticeNo);
		NoticeDTO nPrev = csService.prevNotice(nPrevNo);
		NoticeDTO nNext = csService.nextNotice(nNextNo);
		mv.setViewName("/cscenter/noticeDetail.html");
		mv.addObject("nDetail", nDetail);
		mv.addObject("nPrev", nPrev);
		mv.addObject("nNext", nNext);
		return mv;
	}

	// -------------------------------------------------------------------------------------------
	// FAQ 이동
	@GetMapping("/faq")
	public ModelAndView faqView() throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/faq.html");
		return mv;
	}

	// -------------------------------------------------------------------------------------------
	// 1:1 문의글 목록
	@GetMapping("/questionList")
	public ModelAndView selectQuestionList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ModelAndView mv = new ModelAndView();
		List<QuestionDTO> questionList = csService.selectQuestionList(paramMap, cri);
		mv.addObject("questionList", questionList);

		if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
			mv.addObject("searchCondition", paramMap.get("searchCondition"));
		}

		if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
			mv.addObject("searchKeyword", paramMap.get("searchKeyword"));
		}

		int total = csService.getQuestionTotalCnt(paramMap);
		mv.addObject("pageDTO", new PageDTO(cri, total));

		mv.setViewName("/cscenter/questionList");

		return mv;
	}

	@GetMapping("/pageQList")
	public ResponseEntity<?> pageQList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ResponseDTO<Map<String, Object>> response = new ResponseDTO<>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {

			List<QuestionDTO> questionList = csService.selectQuestionList(paramMap, cri);
			returnMap.put("questionList", questionList);

			if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
				returnMap.put("searchCondition", paramMap.get("searchCondition"));
			}

			if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
				returnMap.put("searchKeyword", paramMap.get("searchKeyword"));
			}

			int total = csService.getQuestionTotalCnt(paramMap);
			returnMap.put("pageDTO", new PageDTO(cri, total));

			response.setItem(returnMap);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	// 1:1 문의글,답변 상세보기
	@GetMapping("/question/{qNo}")
	public ModelAndView selectQuestion(@PathVariable int qNo) {
		QuestionDTO qDetail = csService.selectQuestion(qNo);
		AnswerDTO answer = csService.selectAnswer(qNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/questionDetail.html");
		mv.addObject("qDetail", qDetail);
		mv.addObject("answer", answer);
		return mv;
	}

	// 1:1 문의글 작성 페이지 이동 // 세션 또는 Security
	@GetMapping("/insertQuestion")
	public ModelAndView insertQuestionView() throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/insertQuestion.html");
		return mv;
	}

	// 1:1 문의글 작성
	@PostMapping("/question")
	public void insertQuestion(QuestionDTO questionDTO, HttpServletResponse response, HttpServletRequest request)
			throws IOException {

		csService.insertQuestion(questionDTO);
		response.sendRedirect("/cscenter/questionList");
	}

	// 1:1 문의글 수정 페이지 이동
	@GetMapping("/questionUdt/{qNo}")
	public ModelAndView questionUdtView(@PathVariable int qNo) throws IOException {
		QuestionDTO qDetail = csService.selectQuestion(qNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/questionUdt.html");
		mv.addObject("qDetail", qDetail);
		return mv;
	}

	// 1:1 문의글 수정
	@Transactional
	@PutMapping("/question")
	public ResponseEntity<?> updateQuestion(QuestionDTO questionDTO, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		ResponseDTO<QuestionDTO> responseDTO = new ResponseDTO<>();

		try {

			csService.updateQuestion(questionDTO);

			QuestionDTO returnRep = csService.selectQuestion(questionDTO.getQNo());

			responseDTO.setItem(returnRep);

			return ResponseEntity.ok().body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(responseDTO);
		}
		// response.sendRedirect();
	}

	// 1:1 문의글 삭제
	@DeleteMapping("/question")
	public void deleteQuestion(@RequestParam("qNo") int qNo) {
		csService.deleteQuestion(qNo);
	}

	// -------------------------------------------------------------------------------------------

	// 신고글 목록
	@GetMapping("/repList")
	public ModelAndView selectRepList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ModelAndView mv = new ModelAndView();
		List<RepDTO> repList = csService.selectRepList(paramMap, cri);
		mv.addObject("repList", repList);

		if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
			mv.addObject("searchCondition", paramMap.get("searchCondition"));
		}

		if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
			mv.addObject("searchKeyword", paramMap.get("searchKeyword"));
		}

		int total = csService.getRepTotalCnt(paramMap);
		mv.addObject("pageDTO", new PageDTO(cri, total));

		mv.setViewName("/cscenter/repList.html");

		return mv;
	}

	@GetMapping("/pageRepList")
	public ResponseEntity<?> pageRepList(@RequestParam Map<String, String> paramMap, Criteria cri) {
		ResponseDTO<Map<String, Object>> response = new ResponseDTO<>();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {

			List<RepDTO> repList = csService.selectRepList(paramMap, cri);
			returnMap.put("repList", repList);

			if (paramMap.get("searchCondition") != null && !paramMap.get("searchCondition").equals("")) {
				returnMap.put("searchCondition", paramMap.get("searchCondition"));
			}

			if (paramMap.get("searchKeyword") != null && !paramMap.get("searchKeyword").equals("")) {
				returnMap.put("searchKeyword", paramMap.get("searchKeyword"));
			}

			int total = csService.getRepTotalCnt(paramMap);
			returnMap.put("pageDTO", new PageDTO(cri, total));

			response.setItem(returnMap);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	// 신고글 상세보기
	@GetMapping("/report/{repNo}")
	public ModelAndView selectRep(@PathVariable int repNo) {
		RepDTO rDetail = csService.selectRep(repNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/repDetail.html");
		mv.addObject("rDetail", rDetail);
		return mv;
	}

	// 신고글 작성 페이지 이동
	@GetMapping("/insertReport")
	public ModelAndView insertRepView() throws IOException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/insertReport.html");
		return mv;
	}

	// 신고글 작성
	@PostMapping("/report")
	public void insertRep(RepDTO repDTO, HttpServletResponse response, HttpServletRequest request) throws IOException {

		csService.insertRep(repDTO);
		response.sendRedirect("/cscenter/repList");
	}

	// 신고글 수정 페이지 이동
	@GetMapping("/repUdt/{repNo}")
	public ModelAndView repUdtView(@PathVariable int repNo) {
		RepDTO rDetail = csService.selectRep(repNo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cscenter/repUdt.html");
		mv.addObject("rDetail", rDetail);
		return mv;
	}

	// 신고글 수정
	@Transactional
	@PutMapping("/report")
	public ResponseEntity<?> updateRep(RepDTO repDTO, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		ResponseDTO<RepDTO> responseDTO = new ResponseDTO<>();

		try {
			csService.updateRep(repDTO);

			RepDTO returnRep = csService.selectRep(repDTO.getRepNo());

			responseDTO.setItem(returnRep);

			return ResponseEntity.ok().body(responseDTO);
		} catch (Exception e) {
			responseDTO.setErrorMessage(e.getMessage());

			return ResponseEntity.badRequest().body(responseDTO);
		}
		// response.sendRedirect();
	}

	// 신고글 삭제
	@DeleteMapping("/report")
	public void deleteRep(@RequestParam("repNo") int repNo) {
		csService.deleteRep(repNo);
	}

}
