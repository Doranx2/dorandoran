package com.ezen.doran.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.FaqDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.mapper.AdminMapper;
import com.ezen.doran.service.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	/* DI - AdminMapper 객체를 주입하여 사용 */
	@Autowired
	private AdminMapper adminMapper;
	
	
	/* ======================= AdminNotice ======================= */
	/* INSERT */
	@Override
	public void insertNotice(NoticeDTO noticeDTO) {
		/* 데이터 흐름 : adminController.java -> adminServiceImpl.java -> AdminMapper -> DB */
		adminMapper.insertNotice(noticeDTO);
	}
	
	/* SELECT NOTICE ONE */
	@Override
	public NoticeDTO getNoticeOne(int noticeNo) {
		return adminMapper.selectNoticeOne(noticeNo);
	}
	
	/* UPDATE */
	@Override
	public void updateNotice(NoticeDTO noticeDTO) {
		adminMapper.updateNotice(noticeDTO);
	}
	
	/* SELECT NOTICE ALL */
	@Override
	public List<NoticeDTO> getAdNoticeList(){
		return adminMapper.selectNoticeAll();
	}
	
	/* DELETE NOTICE ONE */
	@Override
	public int deleteNotice(int noticeNo) {
		return adminMapper.deleteNoticeOne(noticeNo);
	}
	
	/* ======================= AdminFAQ ======================= */
	/* INSERT */
	@Override
	public void insertAdFaq(FaqDTO faqDTO) {
		/* 데이터 흐름 : adminController.java -> adminServiceImpl.java -> AdminMapper -> DB */
		adminMapper.insertAdFaq(faqDTO);
	}
	
	/* SELECT NOTICE ONE */
	@Override
	public FaqDTO getAdFaqOne(int faqNo) {
		return adminMapper.selectAdFaqOne(faqNo);
	}
	
	/* UPDATE */
	@Override
	public void updateAdFaq(FaqDTO faqDTO) {
		adminMapper.updateAdFaq(faqDTO);
	}
	
	/* SELECT NOTICE ALL */
	@Override
	public List<FaqDTO> getAdFaqList() {
		return adminMapper.selectAdFaqAll();
	}
	
	/* DELETE NOTICE ONE */
	@Override
	public int deleteAdFaq(int faqNo) {
		return adminMapper.deleteAdFaqOne(faqNo);
	}
	
	/* ======================= AdminQuestionAnswer ======================= */
//	public void insertAdQna(QuestionDTO questionDTO);
//	
//	public QuestionDTO getAdQnaOne(int qNo);
//	
//	public void updateAdQna(QuestionDTO questionDTO);

	/* SELECT ALL */
	public List<QuestionDTO> getAdQnaList(){
		return adminMapper.selectAdQnaAll();
	}
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
	public List<RepDTO> getAdReportList(){
		return adminMapper.selectAdReportAll();
	}
//	
//	public int deleteAdReport(int repNo);

}
