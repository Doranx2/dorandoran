package com.ezen.doran.service.cs;

import java.util.List;
import java.util.Map;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.Criteria;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;

public interface CsService {

	List<NoticeDTO> selectNoticeList(Map<String, String> paramMap, Criteria cri);

	int getNoticeTotalCnt(Map<String, String> paramMap);

	NoticeDTO selectNotice(int noticeNo);
	
	int prevNoticeNo(int noticeNo);

	int nextNoticeNo(int noticeNo);
	
	NoticeDTO prevNotice(int nPrevNo);

	NoticeDTO nextNotice(int nNextNo);
	
	//----------------------------------------------------------------------------------

	List<QuestionDTO> selectQuestionList(Map<String, String> paramMap, Criteria cri);

	int getQuestionTotalCnt(Map<String, String> paramMap);

	QuestionDTO selectQuestion(int qNo);
	
	//----------------------------------------------------------------------------------

	AnswerDTO selectAnswer(int qNo);
	
	//----------------------------------------------------------------------------------

	void insertQuestion(QuestionDTO questionDTO);

	void updateQuestion(QuestionDTO questionDTO);

	void deleteQuestion(int qNo);
	
	//----------------------------------------------------------------------------------

	List<RepDTO> selectRepList(Map<String, String> paramMap, Criteria cri);

	int getRepTotalCnt(Map<String, String> paramMap);

	RepDTO selectRep(int repNo);
	
	//----------------------------------------------------------------------------------

	void insertRep(RepDTO repDTO);

	void updateRep(RepDTO repDTO);

	void deleteRep(int repNo);





}
