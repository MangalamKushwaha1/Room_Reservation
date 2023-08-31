package com.roomreservtaion.repo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.roomreservtaion.vo.RoomReservationVo;

import jakarta.transaction.Transactional;

@Repository
public interface RoomReseravationRepository extends JpaRepository<RoomReservationVo, Long> {

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM room_reservation \r\n"
			+ "WHERE selected_date=:selectedDate  \r\n" + "AND floor=:floor AND room_name=:roomName \r\n" + "AND(\r\n"
			+ "	((start_time\\:\\:time) + interval '1 second' BETWEEN (:startTime\\:\\:time) AND (:endTime\\:\\:time)) OR \r\n"
			+ "((end_time\\:\\:time) - interval '1 second' BETWEEN (:startTime\\:\\:time) AND (:endTime\\:\\:time))\r\n"
			+ ")")
	public int checkAlreadyExist(@Param("floor") String floor, @Param("roomName") String roomName,
			@Param("selectedDate") Date selectedDate, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE room_reservation SET is_deleted=1 WHERE room_reservation_id=:id")
	public int deleteRoomReservation(@Param("id") long id);

	@Query(nativeQuery = true, value = "SELECT * FROM room_reservation WHERE is_deleted=:is_deleted")
	public List<RoomReservationVo> getAllRoomReserved(@Param("is_deleted") int is_deleted);

	@Query(nativeQuery = true, value = "SELECT * FROM room_reservation WHERE room_reservation_id=:id AND is_deleted=0")
	public RoomReservationVo findRoomReservationById(@Param("id") long id);
}
