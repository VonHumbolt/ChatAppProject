package com.kaankaplan.chat_app.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.kaankaplan.chat_app.model.Message;

@Controller
public class ChatController {

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public Message sendMessage(@Payload Message message) {
		return message;
	}
	
	@MessageMapping("/chat.newUser")
	@SendTo("/topic/public")
	public Message registerUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
		
		headerAccessor.getSessionAttributes().put("username", message.getSender());
		
		return message;
	}
}
