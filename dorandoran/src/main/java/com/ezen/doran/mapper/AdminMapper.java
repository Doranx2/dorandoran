package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.FaqDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;

@Mapper
public interface AdminMapper {
	
	/* ======================= AdminNotice ======================= */
	@Select("SELECT * FROM tb_notice")
	List<NoticeDTO> selectNoticeAll();
	
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
			+ "NOW())")
	void insertNotice(NoticeDTO noticeDTO);
	
	@Select("SELECT * FROM tb_notice WHERE NOTICE_NO = #{noticeNo}")
	NoticeDTO selectNoticeOne(int noticeNo);
	
	@Update("UPDATE tb_notice "
			+ "SET NOTICE_TITLE = #{noticeTitle},"
			+ "	   NOTICE_CONTENT = #{noticeContent},"
			+ "	   USER_ID = #{userId}"
			+ "WHERE NOTICE_NO = #{noticeNo}")	
	void updateNotice(NoticeDTO noticeDTO);
	
	@Delete("DELETE FROM tb_notice "
			+ "WHERE NOTICE_NO = #{noticeNo}")
	int deleteNoticeOne(int noticeNo);
		
	/* ======================= AdminFAQ ======================= */
	@Select("SELECT * FROM tb_faq")
	List<FaqDTO> selectAdFaqAll();
	
	@Insert("INSERT INTO tb_faq ( "
			+ "FAQ_NO,"
			+ "FAQ_TITLE,"
			+ "FAQ_CONTENT,"
			+ "USER_ID,"
			+ "INPUT_DTM"
			+ ") VALUES ("
			+ "(SELECT IFNULL(MAX(A.FAQ_NO), 0) + 1 FROM tb_faq A),"
			+ "#{faqTitle},"
			+ "#{faqContent},"
			+ "#{userId},"
			+ "now())")
	void insertAdFaq(FaqDTO faqDTO);
	
	@Select("SELECT * FROM tb_faq WHERE FAQ_NO = #{faqNo}")
	FaqDTO selectAdFaqOne(int faqDTO);
	
	@Update("UPDATE tb_faq "
			+ "SET FAQ_TITLE = #{faqTitle},"
			+ "	   FAQ_CONTENT = #{faqContent},"
			+ "	   USER_ID = #{userId}"
			+ "WHERE FAQ_NO = #{faqNo}")	
	void updateAdFaq(FaqDTO faqDTO);
	
	@Delete("DELETE FROM tb_faq "
			+ "WHERE FAQ_NO = #{tb_faq}")
	int deleteAdFaqOne(int faqDTO);
	
	/* ======================= AdminQuestionAnswer ======================= */
	@Select("SELECT * FROM tb_question")
	List<QuestionDTO> selectAdQnaAll();
	
	/* ======================= AdminReport ======================= */
	@Select("SELECT * FROM tb_rep")
	List<RepDTO> selectAdReportAll();

}

