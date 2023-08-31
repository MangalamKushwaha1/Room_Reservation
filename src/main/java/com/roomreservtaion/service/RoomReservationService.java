package com.roomreservtaion.service;

import java.util.Date;
import java.util.List;

import com.roomreservtaion.vo.RoomReservationVo;

public interface RoomReservationService {

	RoomReservationVo saveData(String name, String floor, String roomName, Date selectedDate, String startTime,
			String endTime, int capacity);

	public List<RoomReservationVo> getAllData(int is_deleted);

	public RoomReservationVo getById(long id);

	public int checkAlreadyExist(String floor, String roomName, Date selectedDate, String startTime, String endTime);

	int deleteRoomReservation(long id);
}
