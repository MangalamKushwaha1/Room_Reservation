package com.roomreservtaion.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "room_reservation")
public class RoomReservationVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_reservation_id")
	private long roomReservationId;

	@Column(name = "room")
	private String room;

	@Column(name = "floor")
	private String floor;

	@Column(name = "room_name")
	private String roomName;

//	@Column(name = "day_selection") // today, tomorrow , multiple day
//	private DaySelection daySelection;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "selected_date")
	@Temporal(TemporalType.DATE)
	private Date selectedDate;

	//@Transient
	//@JsonFormat(pattern = "hh:mm a")
	@Column(name = "start_time")
	private String startTime;

	
	//@Transient
	//@JsonFormat(pattern = "hh:mm a")
	@Column(name = "end_time")
//	@Temporal(TemporalType.TIMESTAMP)
	private String endTime;

	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "is_deleted", length = 1, columnDefinition = "int default 0")
    private int isDeleted;

}
