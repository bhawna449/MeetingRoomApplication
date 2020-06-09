package com.techgig.meetingroombooking.exceptions;

import org.springframework.http.HttpStatus;

public enum ResponseCode implements ErrorCode {

	MEETING_ROOM_001(HttpStatus.NOT_FOUND),

	MEETING_ROOM_002(HttpStatus.NOT_FOUND),

	MEETING_ROOM_003(HttpStatus.NOT_FOUND),

	MEETING_ROOM_004(HttpStatus.NOT_FOUND),

	MEETING_ROOM_005(HttpStatus.INTERNAL_SERVER_ERROR),

	MEETING_ROOM_006(HttpStatus.NOT_FOUND),

	MEETING_ROOM_007(HttpStatus.INTERNAL_SERVER_ERROR);

	/** The http status. */
	private HttpStatus httpStatus;

	/**
	 * Instantiates a new response codes.
	 *
	 * @param httpStatus the http status
	 */
	ResponseCode(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the httpStatus
	 */
	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	@Override
	public String getIdentifier() {
		return name();
	}
}