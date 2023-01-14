package com.ezen.doran.service.chatRoom;

import java.util.List;

import com.ezen.doran.dto.ChatRoomDTO;

public interface ChatRoomService {
	
	List<ChatRoomDTO> selectChatRoomList(int userNo);
	
	ChatRoomDTO selectCharRoom(int roomNo);
	
	void insertChatRoom(ChatRoomDTO chatRoomDTO);
}
