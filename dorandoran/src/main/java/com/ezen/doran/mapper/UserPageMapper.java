package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.MarketDTO;
import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.Pagination2;
import com.ezen.doran.dto.PlayDTO;
import com.ezen.doran.dto.QuestionDTO;
import com.ezen.doran.dto.RepDTO;
import com.ezen.doran.dto.ShareDTO;
import com.ezen.doran.dto.UserDTO;

@Mapper
public interface UserPageMapper {
	
	@Select("SELECT * FROM TB_USER WHERE USER_ID = #{userId}")
	UserDTO selectUser(String userId);
	
	@Update("UPDATE tb_user "
			+ "SET USER_EMAIL = #{userEmail},"
			+ "	   USER_TEL = #{userTel},"
			+ "	   USER_NICK = #{userNick},"
			+ "    USER_LOC = #{userLoc} "
			+ "WHERE USER_ID = #{userId}")
	void updateUser(UserDTO UserDTO);
	
	@Delete("Delete from tb_user "
			+ "Where joinNo = #{joinNo}")
	int DeleteUser(int joinNo);
	
	@Select("SELECT * FROM tb_market"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<MarketDTO> selectMyMarketList(Pagination pagination);
	
	@Select("SELECT * FROM tb_question"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<QuestionDTO> selectMyQuestionList(Pagination2 pagination1);
	
	@Select("SELECT * FROM tb_rep"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<RepDTO> selectMyRepList(Pagination2 pagination2);
	
	@Select("SELECT * FROM tb_share" 
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<ShareDTO> selectMyShareList(Pagination2 pagination1);

	@Select("SELECT * FROM tb_help"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<HelpDTO> selectMyHelpList(Pagination2 pagination2);
	
	@Select("SELECT * FROM tb_play"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<PlayDTO> selectMyPlayList(Pagination2 pagination3);
	
	@Select("SELECT * FROM tb_join"
			+" WHERE USER_NO = #{userNo}"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<JoinDTO> selectMyJoinList(Pagination2 pagination1);
	
	@Select("SELECT COUNT(*) FROM tb_market WHERE USER_NO = #{userNo}")
	int selectMyMarketListCnt(int userNo);
	
	@Select("SELECT COUNT(*) FROM tb_share WHERE USER_NO = #{userNo}")
	int selectMyShareListCnt(int userNo);
	
	@Select("SELECT COUNT(*) FROM tb_help WHERE USER_NO = #{userNo}")
	int selectMyHelpListCnt(int userNo);
	
	@Select("SELECT COUNT(*) FROM tb_play WHERE USER_NO = #{userNo}")
	int selectMyPlayListCnt(int userNo);
	
	@Select("SELECT COUNT(*) FROM tb_question WHERE USER_NO = #{userNo}")
	int selectMyQuestionListCnt(int userNo);

	@Select("SELECT COUNT(*) FROM tb_rep WHERE USER_NO = #{userNo}")
	int selectMyRepListCnt(int userNo);

	@Select("SELECT COUNT(*) FROM tb_join WHERE USER_NO = #{userNo}")
	int selectMyJoinListCnt(int userNo);
	
}
