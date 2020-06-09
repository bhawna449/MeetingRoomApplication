package com.techgig.meetingroombooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techgig.meetingroombooking.request.BookingRequest;
import com.techgig.meetingroombooking.request.CancelRequest;
import com.techgig.meetingroombooking.response.MeetingRoomBookingResponse;
import com.techgig.meetingroombooking.response.MeetingRoomResponse;
import com.techgig.meetingroombooking.service.MeetingRoomService;

@RestController
@RequestMapping(path = "/meeting-room")
public class MeetingRoomController {

	@Autowired
	private MeetingRoomService meetingRoomService;

	/**
	 * Gets the available meeting rooms on the basis of request parameters sent.
	 *
	 * @param buildingName the building name
	 * @param meeetingRoomType the meeeting room type
	 * @param floor the floor
	 * @return the avaialable rooms
	 */
	@GetMapping(value = "/rooms")
	@ResponseBody
	public ResponseEntity<MeetingRoomResponse> getAvaialableRooms(
			@RequestParam(required = true, name = "buildingName") String buildingName,
			@RequestParam(required = true, name = "roomType") String meeetingRoomType,
			@RequestParam(required = false, name = "floor") String floor) {
		MeetingRoomResponse meetingRoomResponse = new MeetingRoomResponse();
		meetingRoomResponse
				.setMeetingRoomList(meetingRoomService.getAvaialableRooms(buildingName, meeetingRoomType, floor));
		return new ResponseEntity<>(meetingRoomResponse, HttpStatus.OK);

	}

	/**
	 * Book a meeting room.
	 *
	 * @param bookingRequest the booking request
	 * @return the response entity
	 */
	@PostMapping(value = "/book-room")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<MeetingRoomBookingResponse> bookMeetingRoom(@RequestBody BookingRequest bookingRequest) {
		String bookingRefernce = meetingRoomService.bookMeetingRoom(bookingRequest.getMeetingRoomId());
		MeetingRoomBookingResponse meetingRoomBookingResponse = new MeetingRoomBookingResponse();
		meetingRoomBookingResponse.setBookingReferenceId(bookingRefernce);
		meetingRoomBookingResponse.setBookingStatus(StringUtils.isEmpty(bookingRefernce) ?false : true);
		
		return new ResponseEntity<>(meetingRoomBookingResponse, HttpStatus.OK);

	}

	/**
	 * Cancel meeting room.
	 *
	 * @param cancelRequest the cancel request
	 */
	@PostMapping(value = "/cancel-room")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public void cancelMeetingRoom(@RequestBody CancelRequest cancelRequest) {
		meetingRoomService.cancelMeetingRoom(cancelRequest.getBookingReference());
	}
}
