package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.AnswerDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.UserDTO;

@Mapper
public interface AdminMapper {
	/* ======================= AdminNotice ======================= */
	/* NOTICE LIST */
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
		
	/* NOTICE INSERT */
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
	
	/* SELECT NOTICE ONE */
	@Select("SELECT * FROM TB_NOTICE JOIN TB_USER USING(USER_ID) WHERE NOTICE_NO = #{noticeNo}")
	NoticeDTO selectNoticeOne(int noticeNo);
	
	/* NOTICE UPDATE */
	@Update("UPDATE TB_NOTICE JOIN TB_USER USING(USER_ID)"
			+ "SET NOTICE_TITLE = #{noticeTitle},"
			+ "	   NOTICE_CONTENT = #{noticeContent},"
			+ "	   USER_ID = #{userId}"
			+ "WHERE NOTICE_NO = #{noticeNo}")	
	void adUpdateNotice(NoticeDTO noticeDTO);
	
	/* DELETE NOTICE ONE */
	@Delete("DELETE FROM TB_NOTICE "
			+ "WHERE NOTICE_NO = #{noticeNo}")
	int adNoticeDeleteOne(int noticeNo);

	/* ======================= AdminQuestionAnswer ======================= */
	/* QNA LIST */
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
	
	/* ANSWER INSERT */
	@Insert("INSERT INTO TB_ANSWER ("
			+ "A_NO,"
			+ "Q_NO,"
			+ "A_CONTENT,"
			+ "INPUT_DTM"
			+ ") VALUES ("
			+ "(SELECT IFNULL(MAX(A.A_NO), 0) + 1 FROM TB_ANSWER A),"
			+ "#{qNo},"
			+ "#{aContent},"
			+ "NOW())")
	void adAnswerInsert(AnswerDTO answerDTO);
	
	/* Question SELECT ONE */
	@Select("SELECT * FROM TB_QUESTION"
			+" JOIN TB_USER USING(USER_NO)"
			+ "WHERE Q_NO = #{qNo}")
	QuestionDTO getQuestionOne(int qNo);
	
	/* Answer SELECT ONE */
	@Select("SELECT Q_NO, A_NO, A_CONTENT, B.INPUT_DTM"
			+" FROM TB_QUESTION A"
			+" LEFT OUTER JOIN TB_ANSWER B USING(Q_NO)"
			+" WHERE Q_NO = #{qNo}")
	AnswerDTO getAnswerOne(int qNo);

	/* ANSWER UPDATE */
	@Update("UPDATE TB_ANSWER JOIN TB_QUESTION USING(Q_NO)"
			+ "SET A_NO = #{aNo},"
			+ "	   A_CONTENT = #{aContent}"
			+ "WHERE Q_NO = #{qNo}")	
	void adAnswerUpdate(AnswerDTO answerDTO);
	
	/* ======================= AdminReport ======================= */
	/* REPORT LIST */
	@Select("SELECT * FROM TB_REP JOIN TB_USER USING(USER_NO)"
			+" WHERE REP_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR REP_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY REP_NO DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<RepDTO> selectReportAll(Pagination pagination);

	@Select("SELECT COUNT(*) FROM TB_REP"
			+" WHERE REP_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR REP_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')")
	int getAdReportTotalCnt(String searchKeyword);
	
	/* REPORT SELECT ONE */
	@Select("SELECT * FROM TB_REP JOIN TB_USER USING(USER_NO)"
			+" WHERE REP_NO = #{repNo}")
	RepDTO getReportOne(int repNo);
	
	/* REPORT UPDATE */
	@Update("UPDATE TB_REP JOIN TB_USER USING(USER_NO)"
			+ "SET COMP_YN = #{compYn}"
			+ "WHERE REP_NO = #{repNo}")
	void adReportUpdate(RepDTO repDTO);
	
	/* ======================= AdminUserRole ======================= */
	/* USER LIST */
	@Select("SELECT * FROM TB_USER"
			+" WHERE USER_NM LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR USER_ID LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY USER_NO DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<UserDTO> selectUserAll(Pagination pagination);

	@Select("SELECT COUNT(*) FROM TB_USER"
			+" WHERE USER_NM LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR USER_ID LIKE CONCAT('%', #{searchKeyword}, '%')")
	int getAdUserTotalCnt(String searchKeyword);

	/* USER SELECT ONE */
	@Select("SELECT * FROM TB_USER"
			+" WHERE USER_NO = #{userNo}")
	UserDTO getUserOne(int userNo);
	
	/* USER UPDATE */
	@Update("UPDATE TB_USER SET USER_ROLE = #{userRole} WHERE USER_NO = #{userNo}")
	void adUserUpdate(UserDTO userDTO);
	
}