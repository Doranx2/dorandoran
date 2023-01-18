package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.HelpDTO;
import com.ezen.doran.dto.JoinDTO;
import com.ezen.doran.dto.NoticeDTO;
import com.ezen.doran.dto.PlayDTO;

@Mapper
public interface HomeMapper {

	@Select("SELECT * FROM TB_NOTICE ORDER BY INPUT_DTM DESC LIMIT 5")
	List<NoticeDTO> selectHomeNoticeList();
	
	@Select("SELECT "
			+ "A.PLAY_TITLE"
			+ ",A.PLAY_NO"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.USER_NO) AS USER_NICK"
			+ " FROM TB_PLAY A ORDER BY A.INPUT_DTM DESC LIMIT 10")
	List<PlayDTO> selectHomePlayList();
	
	@Select("SELECT "
			+ "A.HELP_TITLE"
			+ ",A.HELP_NO"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.USER_NO) AS USER_NICK"
			+ " FROM TB_HELP A ORDER BY A.INPUT_DTM DESC LIMIT 10")
	List<HelpDTO> selectHomeHelpList();
	
	@Select("SELECT "
			+ "A.JOIN_TITLE"
			+ ",A.JOIN_NO"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.USER_NO) AS USER_NICK"
			+ " FROM TB_JOIN A ORDER BY A.INPUT_DTM DESC LIMIT 10")
	List<JoinDTO> selectHomeJoinList();
	
}
