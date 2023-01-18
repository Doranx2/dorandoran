package com.ezen.doran.service.chat.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.ChatDTO;
import com.ezen.doran.mapper.ChatMapper;
import com.ezen.doran.service.chat.ChatService;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatMapper mapper;

	@Override
	public void insertChat(ChatDTO chatDTO) {
		mapper.insertChat(chatDTO);
	}

	@Override
	public List<ChatDTO> selectChat(int roomNo) {
		return mapper.selectChat(roomNo);
	}
	
}
