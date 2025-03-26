package com.fabiogontijo.bank_management_api.core.exception.model;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private MessageType type;

	private String description;

	private List<String> notifications = new ArrayList<>();

	public Message() {
	}

	public Message(final MessageType type, final String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public Message(final MessageType type, final String description, final List<String> notifications) {
		super();
		this.type = type;
		this.description = description;
		this.notifications = notifications;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}

	public Message addNotification(String notification) {
		getNotifications().add(notification);
		return this;
	}

}
