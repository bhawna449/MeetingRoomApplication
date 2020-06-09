package com.techgig.meetingroombooking.response;

import java.util.List;

import com.techgig.meetingroombooking.dto.MeetingRoomDTO;

public class MeetingRoomResponse {

	private List<MeetingRoomDTO> meetingRoomList;

	public List<MeetingRoomDTO> getMeetingRoomList() {
		return meetingRoomList;
	}

	public void setMeetingRoomList(List<MeetingRoomDTO> meetingRoomList) {
		this.meetingRoomList = meetingRoomList;
	}
}
