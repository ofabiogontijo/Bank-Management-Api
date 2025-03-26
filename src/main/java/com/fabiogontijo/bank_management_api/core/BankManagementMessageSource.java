package com.fabiogontijo.bank_management_api.core;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankManagementMessageSource {

	private final MessageSource messageSource;

	public String getMessage(String message) {
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}

	public String getMessage(String message, Object... params) {
		return messageSource.getMessage(message, params, LocaleContextHolder.getLocale());
	}

}
