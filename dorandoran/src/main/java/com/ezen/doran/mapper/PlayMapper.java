package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.doran.dto.Pagination;
import com.ezen.doran.dto.PlayDTO;

@Mapper
public interface PlayMapper {

	@Insert("INSERT INTO TB_PLAY ("
			+ "PLAY_NO,"
			+ "PLAY_TITLE,"
			+ "PLAY_CONTENT,"
			+ "PLAY_CNT,"
			+ "INPUT_DTM,"
			+ "USER_NO"
			+ ") VALUES ("
			+ "(SELECT IFNULL(MAX(A.PLAY_NO), 0) + 1 FROM TB_PLAY A),"
			+ "#{playTitle},"
			+ "#{playContent},"
			+ "0,"
			+ "NOW(),"
			+ "#{userNo})")
	void insertBoard(PlayDTO playDTO);
	
	@Select("SELECT A.PLAY_NO, A.PLAY_TITLE, A.PLAY_CONTENT, A.INPUT_DTM, A.PLAY_CNT, B.USER_NM FROM TB_PLAY A INNER JOIN TB_USER B ON A.USER_NO = B.USER_NO"
			+" WHERE PLAY_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR PLAY_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" ORDER BY A.INPUT_DTM DESC"
			+" LIMIT #{pageSize} OFFSET #{startIndex}")
	List<PlayDTO> selectPlayList(Pagination pagination);
	
	@Select("SELECT COUNT(*) FROM TB_PLAY"
			+" WHERE PLAY_TITLE LIKE CONCAT('%', #{searchKeyword}, '%')"
			+" OR PLAY_CONTENT LIKE CONCAT('%', #{searchKeyword}, '%')")
	int selectPlayCnt(String searchKeyword);
	
	@Select("SELECT * FROM TB_PLAY WHERE PLAY_NO = #{playNo}")
	PlayDTO selectPlay(int playNo);
	
	@Update("UPDATE TB_PLAY "
			+"SET PLAY_TITLE = #{playTitle}, "
			+"PLAY_CONTENT = #{playContent} "
			+"WHERE PLAY_NO = #{playNo}")
	void updatePlay(PlayDTO playDTO);
	
	@Delete("DELETE FROM TB_PLAY WHERE PLAY_NO = #{playNo}")
	void deletePlay(int playNo);
	
	@Update("UPDATE TB_PLAY SET PLAY_CNT = PLAY_CNT+1 WHERE PLAY_NO = #{playNo}")
	void updatePlayCnt(int playNo);
}
