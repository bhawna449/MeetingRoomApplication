package com.techgig.meetingroombooking.response;

public class MeetingRoomBookingResponse {

	private boolean bookingStatus;

	private String bookingReferenceId;

	public boolean isBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingReferenceId() {
		return bookingReferenceId;
	}

	public void setBookingReferenceId(String bookingReferenceId) {
		this.bookingReferenceId = bookingReferenceId;
	}

}
