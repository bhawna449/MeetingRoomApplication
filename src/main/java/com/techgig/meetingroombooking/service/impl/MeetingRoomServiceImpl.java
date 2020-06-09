package com.techgig.meetingroombooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.techgig.meetingroombooking.dto.MeetingRoomDTO;
import com.techgig.meetingroombooking.entity.Building;
import com.techgig.meetingroombooking.entity.Floor;
import com.techgig.meetingroombooking.entity.MeetingRoom;
import com.techgig.meetingroombooking.entity.MeetingRoomBooking;
import com.techgig.meetingroombooking.exceptions.MeetingRoomExceptionMessage;
import com.techgig.meetingroombooking.exceptions.MeetingRoomRuntimeException;
import com.techgig.meetingroombooking.exceptions.ResponseCode;
import com.techgig.meetingroombooking.repository.BuildingRepository;
import com.techgig.meetingroombooking.repository.MeetingRoomBookingRepository;
import com.techgig.meetingroombooking.repository.MeetingRoomRepository;
import com.techgig.meetingroombooking.service.MeetingRoomService;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MeetingRoomServiceImpl.class);

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private MeetingRoomBookingRepository meetingRoomBookingRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	@Override
	public List<MeetingRoomDTO> getAvaialableRooms(String buildingName, String roomType, String floor) {

		List<MeetingRoomDTO> meetingRoomList = new ArrayList<>();
		Optional<Building> building = buildingRepository.findById(buildingName);
		if (!building.isPresent()) {
			throw new MeetingRoomRuntimeException(
					new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_001, buildingName));
		}

		List<Floor> floors = getFloorList(floor, building);
		if (floors.isEmpty()) {
			throw new MeetingRoomRuntimeException(new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_002,
					String.valueOf(floor), buildingName));
		}

		floors.stream().forEach(obj -> {
			obj.getMeetingRooms().forEach(room -> {

				Optional<MeetingRoomBooking> meetingRoom = meetingRoomBookingRepository.findByMeetingRoom(room);

				if (checkMeetingRoomAvailability(roomType, room, meetingRoom)) {
					createMessageRoomDTO(meetingRoomList, obj, room);
				}
			});
		});

		if (meetingRoomList.isEmpty()) {
			throw new MeetingRoomRuntimeException(
					new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_003, roomType, buildingName));
		}
		return meetingRoomList;
	}

	@Override
	public synchronized String bookMeetingRoom(String meetingRoomId) {
		String bookingReference = Strings.EMPTY;

		Optional<MeetingRoom> meetingRoom = meetingRoomRepository.findById(UUID.fromString(meetingRoomId));
		if (!meetingRoom.isPresent()) {
			throw new MeetingRoomRuntimeException(
					new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_004, meetingRoomId));
		}
		Optional<MeetingRoomBooking> meetingRoomBooking = meetingRoomBookingRepository
				.findByMeetingRoom(meetingRoom.get());

		if (!meetingRoomBooking.isPresent()) {
			try {
				bookingReference = UUID.randomUUID().toString();
				MeetingRoomBooking roomBooking = new MeetingRoomBooking();
				roomBooking.setBookingReference(bookingReference);
				roomBooking.setMeetingRoom(meetingRoom.get());
				meetingRoomBookingRepository.save(roomBooking);
			} catch (DataIntegrityViolationException ex) {
				LOGGER.info("Meeting room is already booked");
			} catch (Exception ex) {
				throw new MeetingRoomRuntimeException(
						new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_005, meetingRoomId));
			}

		}
		return bookingReference;
	}

	@Override
	public void cancelMeetingRoom(String bookingRefence) {
		Optional<MeetingRoomBooking> meetingRoomBooking = meetingRoomBookingRepository.findById(bookingRefence);
		if (!meetingRoomBooking.isPresent()) {
			throw new MeetingRoomRuntimeException(
					new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_006, bookingRefence));
		}
		try {
			meetingRoomBookingRepository.deleteById(bookingRefence);
		} catch (Exception e) {
			throw new MeetingRoomRuntimeException(
					new MeetingRoomExceptionMessage(ResponseCode.MEETING_ROOM_007, bookingRefence));
		}
	}

	/**
	 * Check meeting room availability.
	 *
	 * @param roomType    the room type
	 * @param room        the room
	 * @param meetingRoom the meeting room
	 * @return true, if successful
	 */
	private boolean checkMeetingRoomAvailability(String roomType, MeetingRoom room,
			Optional<MeetingRoomBooking> meetingRoom) {
		return !meetingRoom.isPresent() && room.getRoomType().equals(roomType);

	}

	/**
	 * Gets the floor list.
	 *
	 * @param floor    the floor
	 * @param building the building
	 * @return the floor list
	 */
	private List<Floor> getFloorList(String floor, Optional<Building> building) {
		return floor == null ? building.get().getFloors()
				: building.get().getFloors().stream().filter(obj -> obj.getFloor().equals(floor))
						.collect(Collectors.toList());
	}

	/**
	 * Creates the message room DTO.
	 *
	 * @param meetingRoomList the meeting room list
	 * @param obj             the obj
	 * @param room            the room
	 */
	private void createMessageRoomDTO(List<MeetingRoomDTO> meetingRoomList, Floor obj, MeetingRoom room) {
		MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO();
		meetingRoomDTO.setMeetingRoomFloor(obj.getFloor());
		meetingRoomDTO.setMeetingRoomId(room.getMeetingRoomId().toString());
		meetingRoomDTO.setMeetingRoomName(room.getMeetingRoomName());
		meetingRoomList.add(meetingRoomDTO);
	}

}
