package com.techgig.meetingroombooking.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techgig.meetingroombooking.entity.MeetingRoom;

@Repository
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, UUID> {

}
