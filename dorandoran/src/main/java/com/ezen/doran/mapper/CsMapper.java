package com.ezen.doran.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;


@Mapper
public interface CsMapper {

	List<NoticeDTO> selectNoticeList(Map<String, Object> pMap);

	NoticeDTO selectNotice(int noticeNo);

	int getNoticeTotalCnt(Map<String, String> paramMap);
	
	@Select("SELECT IFNULL(MAX(NOTICE_NO),0) FROM TB_NOTICE WHERE NOTICE_NO < #{noticeNo}")
	int prevNoticeNo(int noticeNo);
	
	@Select("SELECT IFNULL(MIN(NOTICE_NO),0) FROM TB_NOTICE WHERE NOTICE_NO > #{noticeNo}")
	int nextNoticeNo(int noticeNo);
	
	NoticeDTO prevNotice(int nPrevNo);

	NoticeDTO nextNotice(int nNextNo);
	
	//----------------------------------------------------------------------------------

	List<QuestionDTO> selectQuestionList(Map<String, Object> pMap);

	QuestionDTO selectQuestion(int qNo);

	int getQuestionTotalCnt(Map<String, String> paramMap);
	
	//----------------------------------------------------------------------------------

	AnswerDTO selectAnswer(int qNo);
	
	//----------------------------------------------------------------------------------

	void insertQuestion(QuestionDTO questionDTO);

	void updateQuestion(QuestionDTO questionDTO);

	void deleteQuestion(int qNo);
	
	//----------------------------------------------------------------------------------

	List<RepDTO> selectRepList(Map<String, Object> pMap);

	RepDTO selectRep(int repNo);

	int getRepTotalCnt(Map<String, String> paramMap);

	//----------------------------------------------------------------------------------
	
	void insertRep(RepDTO repDTO);

	void updateRep(RepDTO repDTO);

	void deleteRep(int repNo);


	
		

	

}
