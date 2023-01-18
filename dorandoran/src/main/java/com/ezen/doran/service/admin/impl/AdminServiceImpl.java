package com.ezen.doran.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.mapper.AdminMapper;
import com.ezen.doran.service.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	/* DI - AdminMapper 객체를 주입하여 사용 */
	@Autowired
	private AdminMapper adminMapper;
	
	
	/* ======================= AdminNotice ======================= */
	/* NOTICE LIST */
	@Override
	public List<NoticeDTO> getAdNoticeList(Pagination page){
		return adminMapper.selectNoticeAll(page);
	}

	@Override
	public int getAdNoticeTotalCnt(String searchKeyword) {
		return adminMapper.getAdNoticeTotalCnt(searchKeyword);
	}
	
	/* NOTICE INSERT */
	@Override
	public void adNoticeInsert(NoticeDTO noticeDTO) {
		/* 데이터 흐름 : adminController.java -> adminServiceImpl.java -> AdminMapper -> DB */
		adminMapper.adNoticeInsert(noticeDTO);
	}
	
	/* SELECT NOTICE ONE */
	@Override
	public NoticeDTO getNoticeOne(int noticeNo) {
		return adminMapper.selectNoticeOne(noticeNo);
	}
	
	/* NOTICE UPDATE */
	@Override
	public void adUpdateNotice(NoticeDTO noticeDTO) {
		adminMapper.adUpdateNotice(noticeDTO);
	}
	
	/* DELETE NOTICE ONE */
	@Override
	public int adNoticeDelete(int noticeNo) {
		return adminMapper.adNoticeDeleteOne(noticeNo);
	}
	
	/* ======================= AdminQuestionAnswer ======================= */
	/* QNA LIST */
	@Override
	public List<QuestionDTO> getAdQnaList(Pagination page){
		return adminMapper.selectQnaAll(page);
	}

	@Override
	public int getAdQnaTotalCnt(String searchKeyword) {
		return adminMapper.getAdQnaTotalCnt(searchKeyword);
	}
	
	/* ANSWER INSERT */
	@Override
	public void adAnswerInsert(AnswerDTO answerDTO) {
		adminMapper.adAnswerInsert(answerDTO);
	}
	
	/* Question SELECT ONE */
	@Override
	public QuestionDTO getQuestionOne(int qNo) {
		return adminMapper.getQuestionOne(qNo);
	}
	
	/* Answer SELECT ONE */
	@Override
	public AnswerDTO getAnswerOne(int qNo) {
		return adminMapper.getAnswerOne(qNo);
	}
		
	/* ANSWER UPDATE */
	@Override
	public void adAnswerUpdate(AnswerDTO answerDTO) {
		adminMapper.adAnswerUpdate(answerDTO);
	}
	
	/* ======================= AdminReport ======================= */
	/* REPORT LIST */
	@Override
	public List<RepDTO> getAdReportList(Pagination page){
		return adminMapper.selectReportAll(page);
	}
		
	@Override
	public int getAdReportTotalCnt(String searchKeyword) {
		return adminMapper.getAdReportTotalCnt(searchKeyword);
	}

	/* REPORT SELECT ONE */
	@Override
	public RepDTO getReportOne(int repNo) {
		return adminMapper.getReportOne(repNo);
	}
	
	/* REPORT UPDATE */
	@Override
	public void adReportUpdate(RepDTO repDTO) {
		adminMapper.adReportUpdate(repDTO);
	}
	
	/* ======================= AdminUserRole ======================= */
	/* USER LIST */
	@Override
	public List<UserDTO> getAdUserList(Pagination page){
		return adminMapper.selectUserAll(page);
	}
		
	@Override
	public int getAdUserTotalCnt(String searchKeyword) {
		return adminMapper.getAdUserTotalCnt(searchKeyword);
	}
	
	/* USER SELECT ONE */
	@Override
	public UserDTO getUserOne(int userNo) {
		return adminMapper.getUserOne(userNo);
	}
	
	/* USER UPDATE */
	@Override
	public void adUserUpdate(UserDTO userDTO) {
		adminMapper.adUserUpdate(userDTO);
	}
	
}
