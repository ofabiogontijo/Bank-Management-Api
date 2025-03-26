package com.fabiogontijo.bank_management_api.core.exception;

import com.fabiogontijo.bank_management_api.core.exception.model.MessageType;

import java.util.List;

public class MoutsTiRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> notifications;

	private MessageType messageType;

	private Integer errorCode;

	public MoutsTiRuntimeException() {
	}

	public MoutsTiRuntimeException(MessageType messageType, List<String> notifications, Integer errorCode) {
		super(new Throwable(notifications.toString()));
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public MoutsTiRuntimeException(MessageType messageType, List<String> notifications, Integer errorCode,
			boolean enableSuppression) {
		super(notifications.toString(), null, enableSuppression, false);
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public MoutsTiRuntimeException(MessageType messageType, List<String> notifications, Throwable cause,
			Integer errorCode) {
		super(cause);
		this.messageType = messageType;
		this.notifications = notifications;
		this.errorCode = errorCode;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public int getHttpErrorCode() {
		return errorCode;
	}

}
