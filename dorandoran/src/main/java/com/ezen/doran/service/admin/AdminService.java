package com.ezen.doran.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;

@Service
public interface AdminService {
	
	/* ======================= AdminNotice ======================= */
	List<NoticeDTO> getAdNoticeList(Pagination pagination);
	
	int getAdNoticeTotalCnt(String searchKeyword);
	
	void adNoticeInsert(NoticeDTO noticeDTO);
	
	NoticeDTO getNoticeOne(int noticeNo);
	
	void adUpdateNotice(NoticeDTO noticeDTO);
	
	public int adNoticeDelete(int noticeNo);

	/* ======================= AdminQuestionAnswer ======================= */
	List<QuestionDTO> getAdQnaList(Pagination pagination);
	
	int getAdQnaTotalCnt(String searchKeyword);
	
	QuestionDTO getQnaOne(int qNo);
	
	AnswerDTO getQnaAnswerOne(int qNo);
	
//	void AdAnswerUpdate(AnswerDTO answerDTO);
//	
//	int adQnaDelete(int qNo);
	
	/* ======================= AdminReport ======================= */

}
