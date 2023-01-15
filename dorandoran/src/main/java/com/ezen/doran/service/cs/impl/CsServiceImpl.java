package com.ezen.doran.service.cs.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.Criteria;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.mapper.CsMapper;
import com.ezen.doran.service.cs.CsService;

@Service
public class CsServiceImpl implements CsService {
	@Autowired
	private CsMapper csMapper;

	@Override
	public List<NoticeDTO> selectNoticeList(Map<String, String> paramMap, Criteria cri) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("noticeSearch", paramMap);
		
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		pMap.put("cri", cri);
		
		return csMapper.selectNoticeList(pMap);
	}

	@Override
	public NoticeDTO selectNotice(int noticeNo) {
		return csMapper.selectNotice(noticeNo);
	}
	
	@Override
	public int getNoticeTotalCnt(Map<String, String> paramMap) {
		return csMapper.getNoticeTotalCnt(paramMap);
	}
	
	@Override
	public int prevNoticeNo(int noticeNo) {
		return csMapper.prevNoticeNo(noticeNo);
	}

	@Override
	public int nextNoticeNo(int noticeNo) {
		return csMapper.nextNoticeNo(noticeNo);
	}
	
	@Override
	public NoticeDTO prevNotice(int nPrevNo) {
		return csMapper.prevNotice(nPrevNo);
	}

	@Override
	public NoticeDTO nextNotice(int nNextNo) {
		return csMapper.nextNotice(nNextNo);
	}

	
	//----------------------------------------------------------------------------------

	@Override
	public List<QuestionDTO> selectQuestionList(Map<String, String> paramMap, Criteria cri) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("questionSearch", paramMap);
		
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		pMap.put("cri", cri);
		
		return csMapper.selectQuestionList(pMap);
	}

	@Override
	public QuestionDTO selectQuestion(int qNo) {
		return csMapper.selectQuestion(qNo);
	}
	
	@Override
	public int getQuestionTotalCnt(Map<String, String> paramMap) {
		return csMapper.getQuestionTotalCnt(paramMap);
	}
	
	//----------------------------------------------------------------------------------
	
	@Override
	public AnswerDTO selectAnswer(int qNo) {
		return csMapper.selectAnswer(qNo);
	}
	
	//----------------------------------------------------------------------------------
	
	@Override
	public void insertQuestion(QuestionDTO questionDTO) {
		csMapper.insertQuestion(questionDTO);
	}

	@Override
	public void updateQuestion(QuestionDTO questionDTO) {
		csMapper.updateQuestion(questionDTO);
	}

	@Override
	public void deleteQuestion(int qNo) {
		csMapper.deleteQuestion(qNo);
	}
	
	//----------------------------------------------------------------------------------

	@Override
	public List<RepDTO> selectRepList(Map<String, String> paramMap, Criteria cri) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		pMap.put("repSearch", paramMap);
		
		cri.setStartNum((cri.getPageNum() - 1) * cri.getAmount());
		pMap.put("cri", cri);
		
		return csMapper.selectRepList(pMap);
	}
	
	@Override
	public RepDTO selectRep(int repNo) {
		return csMapper.selectRep(repNo);
	}

	@Override
	public int getRepTotalCnt(Map<String, String> paramMap) {
		return csMapper.getRepTotalCnt(paramMap);
	}
	
	//----------------------------------------------------------------------------------

	@Override
	public void insertRep(RepDTO repDTO) {
		csMapper.insertRep(repDTO);
	}

	@Override
	public void updateRep(RepDTO repDTO) {
		csMapper.updateRep(repDTO);
	}

	@Override
	public void deleteRep(int repNo) {
		csMapper.deleteRep(repNo);
	}

	
}
