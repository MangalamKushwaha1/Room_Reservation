package com.roomreservtaion.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roomreservtaion.repo.RoomReseravationRepository;
import com.roomreservtaion.vo.RoomReservationVo;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {
	@Autowired
	private RoomReseravationRepository roomReseravationRepository;

//	@Override
//	public RoomReservation saveData(RoomReservation roomReservation) {
//		RoomReservation save = roomReseravationRepo.save(roomReservation);
//		return save;
//	}

	@Override
	public List<RoomReservationVo> getAllData(int is_deleted) {
		return roomReseravationRepository.getAllRoomReserved(is_deleted);
	}

	public int checkAlreadyExist(String floor, String roomName, Date selectedDate, String startTime, String endTime) {
//		RoomReservation set = new RoomReservation();
//		set.setFloor(floor);
//		set.setRoomName(roomName);
//		set.setSelectedDate(selectedDate);
//		set.setStartTime(startTime);
//		set.setEndTime(endTime);

		return roomReseravationRepository.checkAlreadyExist(floor, roomName, selectedDate, startTime, endTime);

	}

	@Override
	public RoomReservationVo saveData(String name, String floor, String roomName, Date selectedDate, String startTime,
			String endTime, int capacity) {
		RoomReservationVo add = new RoomReservationVo();
		add.setRoom(name);
		add.setFloor(floor);
		add.setRoomName(roomName);
		add.setSelectedDate(selectedDate);
		add.setStartTime(startTime);
		add.setEndTime(endTime);
		add.setCapacity(capacity);

		return roomReseravationRepository.save(add);

	}

	// delete room reservation by id
	@Override
	public int deleteRoomReservation(long id) {
		int result = roomReseravationRepository.deleteRoomReservation(id);
		return result;
	}

	@Override
	public RoomReservationVo getById(long id) {
		return roomReseravationRepository.findRoomReservationById(id);
	}
}
