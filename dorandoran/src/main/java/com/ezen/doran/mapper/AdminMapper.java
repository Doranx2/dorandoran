package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.FaqDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;

@Mapper
public interface AdminMapper {
	
	/* ======================= AdminNotice ======================= */
	@Select("SELECT * FROM TB_NOTICE JOIN TB_USER USING(USER_ID)"
			+" WHERE NOTICE_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR NOTICE_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY NOTICE_NO DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<NoticeDTO> selectNoticeAll(Pagination pagination);

	@Select("SELECT COUNT(*) FROM TB_NOTICE JOIN TB_USER USING(USER_ID)"
			+" WHERE NOTICE_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR NOTICE_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')")
	int getAdNoticeTotalCnt(String searchKeyword);
		
	@Insert("INSERT INTO TB_NOTICE ("
			+ "NOTICE_NO,"
			+ "NOTICE_TITLE,"
			+ "NOTICE_CONTENT,"
			+ "USER_ID,"
			+ "INPUT_DTM"
			+ ") VALUES ("
			+ "(SELECT IFNULL(MAX(A.NOTICE_NO), 0) + 1 FROM TB_NOTICE A),"
			+ "#{noticeTitle},"
			+ "#{noticeContent},"
			+ "#{userId},"
			+ "NOW())"
			)
	void adNoticeInsert(NoticeDTO noticeDTO);
	
	@Select("SELECT * FROM TB_NOTICE JOIN TB_USER USING(USER_ID) WHERE NOTICE_NO = #{noticeNo}")
	NoticeDTO selectNoticeOne(int noticeNo);
	
	@Update("UPDATE TB_NOTICE JOIN TB_USER USING(USER_ID)"
			+ "SET NOTICE_TITLE = #{noticeTitle},"
			+ "	   NOTICE_CONTENT = #{noticeContent},"
			+ "	   USER_ID = #{userId}"
			+ "WHERE NOTICE_NO = #{noticeNo}")	
	void adUpdateNotice(NoticeDTO noticeDTO);
	
	@Delete("DELETE FROM TB_NOTICE "
			+ "WHERE NOTICE_NO = #{noticeNo}")
	int adNoticeDeleteOne(int noticeNo);

	/* ======================= AdminQuestionAnswer ======================= */
	@Select("SELECT * FROM TB_QUESTION JOIN TB_USER USING(USER_NO)"
			+" WHERE Q_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR Q_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY Q_NO DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<QuestionDTO> selectQnaAll(Pagination pagination);

	@Select("SELECT COUNT(*) FROM TB_QUESTION"
			+" WHERE Q_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR Q_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')")
	int getAdQnaTotalCnt(String searchKeyword);

	@Select("SELECT * FROM TB_QUESTION JOIN TB_USER USING(USER_NO) WHERE Q_NO = #{qNo}")
	QuestionDTO getQnaOne(int qNo);
	
	@Select("SELECT * FROM TB_ANSWER JOIN TB_QUESTION USING(Q_NO) WHERE A_NO = #{aNo}")
	AnswerDTO getQnaAnswerOne(int aNo);

//	@Update("UPDATE TB_ANSWER JOIN TB_QUESTION USING(Q_NO)"
//			+ "SET A_NO = #{aNo},"
//			+ "    Q_NO = #{qNo},"
//			+ "	   A_CONTENT = #{aContent},"
//			+ "WHERE A_NO = #{aNo}")	
//	void AdAnswerUpdate(AnswerDTO answerDTO);
//	
//	
//	@Delete("DELETE FROM TB_QUESTION "
//			+ "WHERE Q_NO = #{qNo}")
//	int adQnaDelete(int qNo);

	
	/* ======================= AdminReport ======================= */

}

