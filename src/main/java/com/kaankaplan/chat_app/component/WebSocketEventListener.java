package com.kaankaplan.chat_app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.kaankaplan.chat_app.model.Message;
import com.kaankaplan.chat_app.model.MessageType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketEventListener {

	@Autowired
	private SimpMessageSendingOperations sendingOperations;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent sessionConnectedEvent) {
		log.info("New Connection...");
		
	}
	
	@EventListener
	public void handleWebSocketDisConnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
		
		String username = (String) headerAccessor.getSessionAttributes().get("username");
		
		Message message = Message.builder()
				.type(MessageType.DISCONNECT)
				.sender(username)
				.build();
		
		sendingOperations.convertAndSend("/topic/public", message);
	}
}
