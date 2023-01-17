package com.ezen.doran.service.chatRoom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.doran.dto.ChatRoomDTO;
import com.ezen.doran.mapper.ChatRoomMapper;
import com.ezen.doran.service.chatRoom.ChatRoomService;

import groovy.transform.AutoClone;

@Service
public class ChatRoomSerivceImpl implements ChatRoomService{
	@Autowired
	ChatRoomMapper mapper;

	@Override
	public List<ChatRoomDTO> selectChatRoomList(int userNo) {
		return mapper.selectChatRoomList(userNo);
	}

	@Override
	public ChatRoomDTO selectCharRoom(int roomNo) {
		return mapper.selectCharRoom(roomNo);
	}

	@Override
	public void insertChatRoom(ChatRoomDTO chatRoomDTO) {
		mapper.insertChatRoom(chatRoomDTO);
	}

	@Override
	public Integer checkRooms(ChatRoomDTO chatRoomDTO) {
		return mapper.checkRooms(chatRoomDTO);
	}

	@Override
	public void updateChatRoomRead(ChatRoomDTO chatRoomDTO) {
		mapper.updateChatRoomRead(chatRoomDTO);
	}
	
	
}
