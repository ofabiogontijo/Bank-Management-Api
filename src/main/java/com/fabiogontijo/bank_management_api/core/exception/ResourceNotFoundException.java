package com.fabiogontijo.bank_management_api.core.exception;

import com.fabiogontijo.bank_management_api.core.exception.model.MessageType;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Collections.singletonList;

public class ResourceNotFoundException extends BankManagementRuntimeException {

	public ResourceNotFoundException(String notification) {
		super(MessageType.Resource_Not_Found, singletonList(notification), HttpStatus.NOT_FOUND.value());
	}

	public ResourceNotFoundException(List<String> notifications) {
		super(MessageType.Resource_Not_Found, notifications, HttpStatus.NOT_FOUND.value());
	}

	public ResourceNotFoundException(List<String> notifications, Throwable cause) {
		super(MessageType.Resource_Not_Found, notifications, cause, HttpStatus.NOT_FOUND.value());
	}

	public ResourceNotFoundException(String notification, boolean enableSuppression) {
		super(MessageType.Resource_Not_Found, singletonList(notification), HttpStatus.NOT_FOUND.value(),
				enableSuppression);
	}

}
