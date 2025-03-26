package com.fabiogontijo.bank_management_api.core.exception;

import com.fabiogontijo.bank_management_api.core.exception.model.MessageType;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Collections.singletonList;

public class BusinessLogicException extends MoutsTiRuntimeException {

	public BusinessLogicException(String notifications) {
		super(MessageType.Business_Logic_Error, singletonList(notifications), HttpStatus.PRECONDITION_FAILED.value());
	}

	public BusinessLogicException(List<String> notifications) {
		super(MessageType.Business_Logic_Error, notifications, HttpStatus.PRECONDITION_FAILED.value());
	}

	public BusinessLogicException(List<String> notifications, Throwable cause) {
		super(MessageType.Business_Logic_Error, notifications, cause, HttpStatus.PRECONDITION_FAILED.value());
	}

	public BusinessLogicException(List<String> notifications, boolean enableSuppression) {
		super(MessageType.Business_Logic_Error, notifications, HttpStatus.PRECONDITION_FAILED.value(),
				enableSuppression);
	}

	public BusinessLogicException(String notifications, boolean enableSuppression) {
		super(MessageType.Business_Logic_Error, singletonList(notifications), HttpStatus.PRECONDITION_FAILED.value(),
				enableSuppression);
	}

}
