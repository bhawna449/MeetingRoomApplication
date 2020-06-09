package com.techgig.meetingroombooking.exceptions;

public class MeetingRoomRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** The error message. */
	private final MeetingRoomExceptionMessage errorMessage;

	/**
	 * Instantiates a new ATM runtime exception.
	 *
	 * @param errorMessage the error message
	 * @param cause        the cause
	 */
	public MeetingRoomRuntimeException(final MeetingRoomExceptionMessage errorMessage, final Throwable cause) {
		super(errorMessage.getCode().getIdentifier(), cause);
		this.errorMessage = errorMessage;
	}

	/**
	 * Instantiates a new ATM runtime exception.
	 *
	 * @param errorMessage the error message
	 */
	public MeetingRoomRuntimeException(final MeetingRoomExceptionMessage errorMessage) {
		super(errorMessage.getCode().getIdentifier());
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorMessage.getCode();
	}

	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public String[] getArgs() {
		return errorMessage.getArgs();
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return errorMessage.getReason();
	}

	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder(super.getMessage());
		if (getReason() != null) {
			builder.append(" Reason: ").append(getReason());
		}
		return builder.toString();
	}

}
