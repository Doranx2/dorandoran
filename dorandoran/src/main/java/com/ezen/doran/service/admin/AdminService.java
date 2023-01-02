package com.ezen.doran.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.FaqDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;

@Service
public interface AdminService {
	
	/* ======================= AdminNotice ======================= */
	public void insertNotice(NoticeDTO noticeDTO);
	
	public NoticeDTO getNoticeOne(int noticeNo);
	
	public void updateNotice(NoticeDTO noticeDTO);
	
	public List<NoticeDTO> getAdNoticeList();
	
	public int deleteNotice(int noticeNo);
	
	/* ======================= AdminFAQ ======================= */
	public void insertAdFaq(FaqDTO faqDTO);
	
	public FaqDTO getAdFaqOne(int faqNo);
	
	public void updateAdFaq(FaqDTO faqDTO);
	
	public List<FaqDTO> getAdFaqList();
	
	public int deleteAdFaq(int faqNo);
	
	/* ======================= AdminQuestionAnswer ======================= */
//	public void insertAdQna(QuestionDTO questionDTO);
//	
//	public QuestionDTO getAdQnaOne(int qNo);
//	
//	public void updateAdQna(QuestionDTO questionDTO);
//	
	public List<QuestionDTO> getAdQnaList();
//	
//	public int deleteAdQna(int qNo);
//	
//	public void updateAdAnswer(AnswerDTO answerDTO);
	
	/* ======================= AdminReport ======================= */
//	public void insertAdReport(RepDTO repDTO);
//	
//	public RepDTO getAdReportOne(int repNo);
//	
//	public void updateAdReport(RepDTO repDTO);
//	
	public List<RepDTO> getAdReportList();
//	
//	public int deleteAdReport(int repNo);
}
