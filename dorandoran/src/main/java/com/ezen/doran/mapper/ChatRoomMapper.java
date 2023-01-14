package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.ChatRoomDTO;

@Mapper
public interface ChatRoomMapper {
	
	
	@Select("SELECT * FROM TB_CHAT_ROOM"
			+ " WHERE MASTER_NO = #{userNo} OR SLAVE_NO = #{userNo}")
	List<ChatRoomDTO> selectChatRoomList(int userNo);
	
	@Select("SELECT * FROM TB_CHAT_ROOM WHERE ROOM_NO = #{roomNo}")
	ChatRoomDTO selectCharRoom(int roomNo);
	
//	@Insert("INSERT INTO TB_CHAT_ROOM (ROOM_NO,ROOM_NM,MASTER_NO,SLAVE_NO) "
//			+ " VALUES ((SELECT IFNULL(MAX(ROOM_NO),0)+1 FROM TB_CHAT_ROOM A),#{roomNm},#{masterNo},#{slaveNo})")
	void insertChatRoom(ChatRoomDTO chatRoomDTO); 
}
