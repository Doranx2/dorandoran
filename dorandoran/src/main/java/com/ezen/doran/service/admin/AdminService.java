package com.ezen.doran.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.UserDTO;

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
	
	void adAnswerInsert(AnswerDTO answerDTO);

	QuestionDTO getQuestionOne(int qNo);
	
	AnswerDTO getAnswerOne(int qNo);
	
	void adAnswerUpdate(AnswerDTO answerDTO);
	
	/* ======================= AdminReport ======================= */
	List<RepDTO> getAdReportList(Pagination pagination);
	
	int getAdReportTotalCnt(String searchKeyword);
	
	RepDTO getReportOne(int repNo);
	
	void adReportUpdate(RepDTO repDTO);
	
	/* ======================= AdminUserRole ======================= */
	List<UserDTO> getAdUserList(Pagination pagination);
	
	int getAdUserTotalCnt(String searchKeyword);
	
	UserDTO getUserOne(int userNo);
	
	void adUserUpdate(UserDTO userDTO);

}
