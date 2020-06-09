package com.techgig.meetingroombooking;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techgig.meetingroombooking.entity.Building;
import com.techgig.meetingroombooking.entity.Floor;
import com.techgig.meetingroombooking.entity.MeetingRoom;
import com.techgig.meetingroombooking.entity.RoomType;
import com.techgig.meetingroombooking.repository.BuildingRepository;
import com.techgig.meetingroombooking.repository.FloorRepository;
import com.techgig.meetingroombooking.repository.MeetingRoomRepository;

@Component
public class MeetingRoomDataPreparation {

	@Autowired
	BuildingRepository buildingRepository;

	@Autowired
	FloorRepository floorRepository;

	@Autowired
	MeetingRoomRepository meetingRoomRepository;

	@PostConstruct
	public void save() {
		MeetingRoom meetingRoom1 = new MeetingRoom();
		meetingRoom1.setMeetingRoomName("Room 1");
		meetingRoom1.setRoomType(RoomType.EIGHT_SEATER.getDisplayName());

		MeetingRoom meetingRoom2 = new MeetingRoom();
		meetingRoom2.setMeetingRoomName("Room 2");
		meetingRoom2.setRoomType(RoomType.EIGHT_SEATER.getDisplayName());

		MeetingRoom meetingRoom3 = new MeetingRoom();
		meetingRoom3.setMeetingRoomName("Room 3");
		meetingRoom3.setRoomType(RoomType.FOUR_SEATER.getDisplayName());

		MeetingRoom meetingRoom4 = new MeetingRoom();
		meetingRoom4.setMeetingRoomName("Room 4");
		meetingRoom4.setRoomType(RoomType.FOUR_SEATER.getDisplayName());

		Floor floor1 = new Floor();
		Floor floor2 = new Floor();
		Floor floor3 = new Floor();
		Floor floor4 = new Floor();
		Floor floor5 = new Floor();

		floor1.setFloor("1");
		floor1.getMeetingRooms().add(meetingRoom1);

		floor2.setFloor("2");
		floor2.getMeetingRooms().add(meetingRoom2);

		floor3.setFloor("1");
		floor3.getMeetingRooms().add(meetingRoom3);

		floor4.setFloor("2");
		floor4.getMeetingRooms().add(meetingRoom4);

		floor5.setFloor("1");

		Building building1 = new Building();
		Building building2 = new Building();
		Building building3 = new Building();

		building1.setBuildingName("Building1");
		building1.getFloors().add(floor1);
		building1.getFloors().add(floor2);

		building2.setBuildingName("Building2");
		building2.getFloors().add(floor3);
		building2.getFloors().add(floor4);

		building3.setBuildingName("Building3");
		building3.getFloors().add(floor5);

		floor1.setBuilding(building1);
		floor2.setBuilding(building1);
		floor3.setBuilding(building2);
		floor4.setBuilding(building2);
		floor5.setBuilding(building3);

		meetingRoom1.setFloor(floor1);
		meetingRoom2.setFloor(floor2);
		meetingRoom3.setFloor(floor3);
		meetingRoom4.setFloor(floor4);

		buildingRepository.save(building1);
		floorRepository.save(floor1);
		floorRepository.save(floor2);
		meetingRoomRepository.save(meetingRoom1);
		meetingRoomRepository.save(meetingRoom2);

		buildingRepository.save(building2);
		floorRepository.save(floor3);
		floorRepository.save(floor4);
		meetingRoomRepository.save(meetingRoom3);
		meetingRoomRepository.save(meetingRoom4);

		buildingRepository.save(building3);
		floorRepository.save(floor5);
	}
}
