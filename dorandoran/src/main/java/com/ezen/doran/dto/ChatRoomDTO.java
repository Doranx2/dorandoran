package com.ezen.doran.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO {
	private int roomNo;
	private String roomNm;
	private int masterNo;
	private int slaveNo;
	
	
	private Set<WebSocketSession> sessions = new HashSet<>();
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

//    public static ChatRoomDTO create(String roomNm){
//        ChatRoomDTO room = new ChatRoomDTO();
//
//        room.roomNo = UUID.randomUUID().toString(); // roomNo 자동 생성
//        room.roomNm = roomNm;
//        return room;
//    }
}
