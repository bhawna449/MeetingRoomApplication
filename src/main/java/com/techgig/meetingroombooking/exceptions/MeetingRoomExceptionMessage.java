package com.techgig.meetingroombooking.exceptions;

import java.util.Arrays;

/**
 * The Class ATM ExceptionMessage.
 */
public class MeetingRoomExceptionMessage {

    /** The error code. */
    private ErrorCode code;

    /** The arguments */
    private String[] args;

    /** The reason. */
    private String reason;

    /**
     * Instantiates a new ATM exception data.
     *
     * @param type
     *            the type
     * @param code
     *            the code
     * @param args
     *            the arguments
     */
    public MeetingRoomExceptionMessage(ErrorCode code, String... args) {
        super();
        this.code = code;
        if (args != null) {
            this.args = Arrays.copyOf(args, args.length);
        } else {
            this.args = new String[0];
        }
    }


    /**
     * Sets the reason.
     *
     * @param reason
     *            the reason
     * @return the ATM exception message
     */
    public MeetingRoomExceptionMessage reason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public ErrorCode getCode() {
        return code;
    }

    /**
     * Gets the argument.
     *
     * @return the argument
     */
    public String[] getArgs() {
        return args.clone();
    }

    /**
     * Gets the reason for exception.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Exception [code=");
        builder.append(code);
        builder.append(", type=");
        builder.append(", args=");
        builder.append(Arrays.toString(args));
        if (null != reason) {
            builder.append(", reason=");
            builder.append(reason);
        }
        builder.append("]");
        return builder.toString();
    }

}
