package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.ChatDTO;

@Mapper
public interface ChatMapper {
	
	@Insert("INSERT INTO TB_CHAT VALUES "
			+ "( (SELECT IFNULL(MAX(A.CHAT_NO),0)+1 FROM TB_CHAT A)"
			+ ", #{roomNo}"
			+ ", #{sendUserNo}"
			+ ", #{message}"
			+ ", now()"
			+ ", 'N'"
			+ ")")
	void insertChat(ChatDTO chatDTO);
	
	@Select("SELECT * FROM TB_CHAT WHERE ROOM_NO = #{roomNo}")
	List<ChatDTO> selectChat(int roomNo);
}
