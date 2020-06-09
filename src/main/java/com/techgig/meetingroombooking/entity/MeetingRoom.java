package com.techgig.meetingroombooking.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MeetingRoom {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false, unique = true, columnDefinition = "BINARY(16)")
	private UUID meetingRoomId;

	private String meetingRoomName;

	private String roomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id")
	Floor floor;

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public UUID getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(UUID meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	public String getMeetingRoomName() {
		return meetingRoomName;
	}

	public void setMeetingRoomName(String meetingRoomName) {
		this.meetingRoomName = meetingRoomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

}
