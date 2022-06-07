package com.kaankaplan.chat_app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
	
	private MessageType type;
	
	private String content;
	
	private String sender;
	
	private String time;
	
}
