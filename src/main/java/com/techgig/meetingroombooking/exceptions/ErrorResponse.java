package com.techgig.meetingroombooking.exceptions;

public class ErrorResponse {

	/** The identifier. */
	private String identifier;

	/** The message. */
	private String message;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
