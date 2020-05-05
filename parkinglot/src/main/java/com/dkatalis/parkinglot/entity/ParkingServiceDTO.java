package com.dkatalis.parkinglot.entity;

import java.util.HashMap;

public class ParkingServiceDTO {

	private HashMap<String, StringBuilder> messages;

	public ParkingServiceDTO() {
		this.messages = new HashMap<String, StringBuilder>();
	}
	
	public void addNewMessage(String key, Object value) {
		messages.put(key, new StringBuilder(value.toString()));
	}
	
	public HashMap<String, StringBuilder> getMessages() {
		return messages;
	}

	public void setMessages(HashMap<String, StringBuilder> messages) {
		this.messages = messages;
	}

}
