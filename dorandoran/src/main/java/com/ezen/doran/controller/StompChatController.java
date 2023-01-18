package com.ezen.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ezen.doran.dto.ChatDTO;
import com.ezen.doran.dto.ChatRoomDTO;
import com.ezen.doran.dto.UserDTO;
import com.ezen.doran.mapper.UserMapper;
import com.ezen.doran.service.chat.ChatService;
import com.ezen.doran.service.chatRoom.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	UserMapper userMapper;
	
	private final SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달

	// Client가 SEND할 수 있는 경로
	// stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// "/pub/chat/enter"
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatDTO message) {
		UserDTO userDTO = userMapper.getUserNm(message.getSendUserNo());
		
//		ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
//		chatRoomDTO.setRoomNo(message.getRoomNo());
//		chatRoomDTO.setUserNo(message.getSendUserNo());
//		chatRoomService.updateChatRoomRead(chatRoomDTO);
		
		message.setMessage( userDTO.getUserNick()+ "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
	}

	@MessageMapping(value = "/chat/message")
	public void message(ChatDTO message) {
		chatService.insertChat(message);
		template.convertAndSend("/sub/chat/room/" + message.getRoomNo(), message);
	}
}
