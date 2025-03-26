package com.fabiogontijo.bank_management_api.core.exception.handler;

import com.fabiogontijo.bank_management_api.core.exception.*;
import com.fabiogontijo.bank_management_api.core.exception.model.Message;
import com.fabiogontijo.bank_management_api.core.exception.model.MessageType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Set;

import static java.util.Collections.singletonList;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=utf-8";

	private static final String ERROR = "error";

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public HttpEntity<Message> handlerValidationException(MethodArgumentTypeMismatchException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Parameter_Error, MessageType.Parameter_Error.getDescription(),
				singletonList("Invalid parameter: " + ex.getParameter().getParameterName() + " with value: "
						+ ex.getValue()));
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public HttpEntity<Message> handlerValidationException(ConstraintViolationException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = processConstraintViolation(ex);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public HttpEntity<Message> handlerValidationException(BindException ex) {
		LOGGER.debug(ERROR, ex);
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Message message = processFieldErrors(fieldErrors);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HttpEntity<Message> handlerValidationException(MethodArgumentNotValidException ex) {
		LOGGER.debug(ERROR, ex);
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Message message = processFieldErrors(fieldErrors);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ServletRequestBindingException.class, HttpMessageNotReadableException.class,
			IllegalArgumentException.class })
	public HttpEntity<Message> handlerMissingServletRequestParameterException(Exception ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Parameter_Error, MessageType.Parameter_Error.getDescription(),
				singletonList(ex.getMessage()));
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);

	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public HttpEntity<Message> handlerHttpMediaTypeNotSupportedException(Exception ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Unsupported_Media_Type,
				MessageType.Unsupported_Media_Type.getDescription());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public HttpEntity<Message> handlerHttpRequestMethodNotSupportedException(Exception ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Method_Not_Allowed, MessageType.Method_Not_Allowed.getDescription());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public HttpEntity<Message> handlerResourceNotFoundException(final ResourceNotFoundException ex) {
		LOGGER.info(ERROR, ex);
		Message message = new Message(MessageType.Resource_Not_Found, MessageType.Resource_Not_Found.getDescription(),
				ex.getNotifications());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ExceptionHandler(BusinessLogicException.class)
	public HttpEntity<Message> handlerBusinessLogicException(BusinessLogicException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Business_Logic_Error,
				MessageType.Business_Logic_Error.getDescription(), ex.getNotifications());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.PRECONDITION_FAILED);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public HttpEntity<Message> handlerBadRequestException(BadRequestException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Bad_Request_Error, MessageType.Bad_Request_Error.getDescription(),
				ex.getNotifications());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, Error.class })
	public HttpEntity<Message> handlerException(Exception ex) {
		LOGGER.info(ERROR, ex);
		Message message = new Message(MessageType.Internal_Architecture_Error,
				MessageType.Internal_Architecture_Error.getDescription());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalArchitectureException.class)
	public HttpEntity<Message> handlerInternalArchitectureException(InternalArchitectureException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Internal_Architecture_Error,
				MessageType.Internal_Architecture_Error.getDescription(), ex.getNotifications());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConflictException.class)
	public HttpEntity<Message> handlerResourceConflictException(ConflictException ex) {
		LOGGER.debug(ERROR, ex);
		Message message = new Message(MessageType.Conflict_Error, MessageType.Conflict_Error.getDescription(),
				ex.getNotifications());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.CONFLICT);
	}

	private Message processFieldErrors(List<FieldError> fieldErrors) {
		Message message = new Message(MessageType.Parameter_Error, MessageType.Parameter_Error.getDescription());
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = fieldError.getDefaultMessage();
			message.addNotification(fieldError.getField().concat(":").concat(localizedErrorMessage));
		}
		return message;
	}

	private Message processConstraintViolation(ConstraintViolationException ex) {
		Message message = new Message(MessageType.Parameter_Error, MessageType.Parameter_Error.getDescription());
		Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
		for (ConstraintViolation<?> next : set) {
			message.addNotification(
					((PathImpl) next.getPropertyPath()).getLeafNode().getName() + ":" + next.getMessage());
		}
		return message;
	}

	private HttpEntity<Message> handleSecurityException(Exception ex) {
		Message message = new Message(MessageType.Access_Denied, MessageType.Access_Denied.getDescription(),
				singletonList(ex.getMessage()));
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_CHARSET_UTF_8);
		return new ResponseEntity<>(message, responseHeaders, HttpStatus.FORBIDDEN);
	}

}
