package com.ezen.doran.service.chat;

import java.util.List;

import com.ezen.doran.dto.ChatDTO;

public interface ChatService {
	void insertChat(ChatDTO chatDTO);
	
	List<ChatDTO> selectChat(int roomNo);
}
