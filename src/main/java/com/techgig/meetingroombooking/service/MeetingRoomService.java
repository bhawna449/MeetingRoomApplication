package com.techgig.meetingroombooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techgig.meetingroombooking.dto.MeetingRoomDTO;

@Service
public interface MeetingRoomService {

	/**
	 * Gets the avaialable rooms.
	 *
	 * @param buildingName the building name
	 * @param roomType the room type
	 * @param floor the floor
	 * @return the avaialable rooms
	 */
	public List<MeetingRoomDTO> getAvaialableRooms(final String buildingName, final String roomType,
			final String floor);

	/**
	 * Book meeting room.
	 *
	 * @param meetingRoomId the meeting room id
	 * @return the string
	 */
	public String bookMeetingRoom(final String meetingRoomId);

	/**
	 * Cancel meeting room.
	 *
	 * @param bookingRefence the booking refence
	 */
	public void cancelMeetingRoom(final String bookingRefence);
}
