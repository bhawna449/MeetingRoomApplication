package com.techgig.meetingroombooking.exceptions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeetingRoomExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Method to handle InvalidMediaTypeException when user given media type other
	 * than supported one.
	 *
	 * @param req the actual request
	 * @param ex  the exception got while executing request
	 * @return the response entity
	 */
	@ExceptionHandler({ MeetingRoomRuntimeException.class })
	public ResponseEntity<ErrorResponse> exceptionHandle(final HttpServletRequest req,
			final MeetingRoomRuntimeException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		final Locale locale = LocaleContextHolder.getLocale();
		errorResponse.setIdentifier(ex.getErrorCode().getIdentifier());
		errorResponse.setMessage(messageSource.getMessage(ex.getErrorCode().getIdentifier(), ex.getArgs(), locale));
		return new ResponseEntity<ErrorResponse>(errorResponse, ex.getErrorCode().getHttpStatus());
	}
}
