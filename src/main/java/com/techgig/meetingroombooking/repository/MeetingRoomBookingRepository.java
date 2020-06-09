package com.techgig.meetingroombooking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techgig.meetingroombooking.entity.MeetingRoom;
import com.techgig.meetingroombooking.entity.MeetingRoomBooking;

@Repository
public interface MeetingRoomBookingRepository
		extends CrudRepository<com.techgig.meetingroombooking.entity.MeetingRoomBooking, String> {

	Optional<MeetingRoomBooking> findByMeetingRoom(MeetingRoom meetingRoom);
}
