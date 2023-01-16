package com.ezen.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ezen.doran.dto.ChatRoomDTO;

@Mapper
public interface ChatRoomMapper {
	
	
	@Select("SELECT"
			+ " A.ROOM_NO"
			+ ",A.ROOM_NM"
			+ ",A.MASTER_NO"
			+ ",A.SLAVE_NO"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.MASTER_NO) AS MASTER_NM"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.SLAVE_NO) AS SLAVE_NM"
			+ " FROM TB_CHAT_ROOM A"
			+ " WHERE A.MASTER_NO = #{userNo} OR A.SLAVE_NO = #{userNo}")
	List<ChatRoomDTO> selectChatRoomList(int userNo);
	
	@Select("SELECT ROOM_NO FROM TB_CHAT_ROOM"
			+ " WHERE ( MASTER_NO = #{masterNo} OR SLAVE_NO = #{masterNo} )"
			+ " AND"
			+ " ( MASTER_NO = #{slaveNo} OR SLAVE_NO = #{slaveNo} )")
	int checkRooms(ChatRoomDTO chatRoomDTO);
	
	
	@Select("SELECT "
			+ " A.ROOM_NO"
			+ ",A.ROOM_NM"
			+ ",A.MASTER_NO"
			+ ",A.SLAVE_NO"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.MASTER_NO) AS MASTER_NM"
			+ ",( SELECT B.USER_NICK FROM TB_USER B WHERE B.USER_NO = A.SLAVE_NO) AS SLAVE_NM"
			+ " FROM TB_CHAT_ROOM A WHERE ROOM_NO = #{roomNo}")
	ChatRoomDTO selectCharRoom(int roomNo);
	
//	@Insert("INSERT INTO TB_CHAT_ROOM (ROOM_NO,ROOM_NM,MASTER_NO,SLAVE_NO) "
//			+ " VALUES ((SELECT IFNULL(MAX(ROOM_NO),0)+1 FROM TB_CHAT_ROOM A),#{roomNm},#{masterNo},#{slaveNo})")
	void insertChatRoom(ChatRoomDTO chatRoomDTO); 
}
