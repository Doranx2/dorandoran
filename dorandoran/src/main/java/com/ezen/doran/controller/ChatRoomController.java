package com.ezen.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.doran.dto.ChatRoomDTO;
import com.ezen.doran.service.chat.ChatService;
import com.ezen.doran.service.chatRoom.ChatRoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class ChatRoomController {
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	ChatService chatService;

	// 채팅방 목록 조회
	@GetMapping(value = "/rooms")
	public ModelAndView rooms(@RequestParam("userNo") int userNo) {

		log.info("# All Chat Rooms");
		ModelAndView mv = new ModelAndView("chat/rooms");

		mv.addObject("list", chatRoomService.selectChatRoomList(userNo));

		return mv;
	}

	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(ChatRoomDTO chatRoomDTO, RedirectAttributes rttr) {

		log.info("# Create Chat Room , name: " + chatRoomDTO.getRoomNm());
		chatRoomService.insertChatRoom(chatRoomDTO);
		ChatRoomDTO room = chatRoomService.selectCharRoom(chatRoomDTO.getRoomNo());
		rttr.addFlashAttribute("room", room);
		return "redirect:/chat/room?roomNo="+chatRoomDTO.getRoomNo();
	}

	// 채팅방 조회
	@GetMapping("/room")
	public void getRoom(int roomNo, Model model) {

		log.info("# get Chat Room, roomID : " + roomNo);

		model.addAttribute("chat", chatService.selectChat(roomNo));
		model.addAttribute("room", chatRoomService.selectCharRoom(roomNo));
	}
}
