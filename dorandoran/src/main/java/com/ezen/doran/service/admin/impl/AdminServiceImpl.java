package com.ezen.doran.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.mapper.AdminMapper;
import com.ezen.doran.service.admin.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	/* DI - AdminMapper 객체를 주입하여 사용 */
	@Autowired
	private AdminMapper adminMapper;
	
	
	/* ======================= AdminNotice ======================= */
	/* SELECT NOTICE ALL */
	@Override
	public List<NoticeDTO> getAdNoticeList(Pagination page){
		return adminMapper.selectNoticeAll(page);
	}

	@Override
	public int getAdNoticeTotalCnt(String searchKeyword) {
		return adminMapper.getAdNoticeTotalCnt(searchKeyword);
	}
	
	/* INSERT */
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
	
	/* UPDATE */
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
	/* LIST */
	@Override
	public List<QuestionDTO> getAdQnaList(Pagination page){
		return adminMapper.selectQnaAll(page);
	}

	@Override
	public int getAdQnaTotalCnt(String searchKeyword) {
		return adminMapper.getAdQnaTotalCnt(searchKeyword);
	}
	
	@Override
	public QuestionDTO getQnaOne(int qNo) {
		return adminMapper.getQnaOne(qNo);
	}
	
	@Override
	public AnswerDTO getQnaAnswerOne(int qNo) {
		return adminMapper.getQnaAnswerOne(qNo);
	}
	
//	@Override
//	public void AdAnswerUpdate(AnswerDTO answerDTO) {
//		adminMapper.AdAnswerUpdate(answerDTO);
//	}	
//	
//	@Override
//	public int adQnaDelete(int qNo) {
//		return adminMapper.adQnaDelete(qNo);
//	}
	
	/* ======================= AdminReport ======================= */


}
