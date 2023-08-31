package com.roomreservtaion.ctrl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.roomreservtaion.service.RoomReservationService;
import com.roomreservtaion.vo.RoomReservationVo;

@RestController
@RequestMapping("/api/v1/reservation")
public class RoomReservationController {

	@Autowired
	private RoomReservationService roomReservationService;

//	@PostMapping("/save")
//	public ModelAndView saveDataOfPage(@ModelAttribute RoomReservation roomReservation) {
//		ModelAndView mv=new ModelAndView("addreservation");
//		RoomReservation savedReservation=roomReservationService.saveData(roomReservation);
//		mv.addObject("savedReservation",savedReservation);
//		return mv;		
//		
////		roomReservationService.saveData(roomReservation);
////		return "addreservation";
//	}

//	@PostMapping("/new")
//	public ModelAndView getUserform(@RequestParam("floor") String floor,@RequestParam("roomName") String roomName,@RequestParam("selectedDate") LocalDate selectedDate,@RequestParam("startTime") LocalTime startTime,@RequestParam("endTime") LocalTime endTime) {
//		ModelAndView modelAndView = new ModelAndView("addreservation");
//		return modelAndView ; 
//	}

	// rest api
	
//	1. /save
//	2. /getall or /fetchall --> get all record by is_deleted = 0
//	3. /get/{id} --> get by roomrevid
//	4. /delete/{id} --> delete by roomrevid
//	5. /update/{id} --> update by roomrevid
	

	@PostMapping("/save")
	public RoomReservationVo saveDataOfPage(@RequestParam("name") String name, @RequestParam("floor") String floor,
			@RequestParam("roomName") String roomName, @RequestParam("selectedDate") String selectedDateString,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("capacity") int capacity) throws ParseException {

//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

//		
//		Date startTime = sdf3.parse(selectedDateString + " " + startTimeString);
//		Date endTime = sdf3.parse(selectedDateString + " " + startTimeString);
		Date selectedDate = sdf2.parse(selectedDateString);
		
		System.out.println(name+ floor+ roomName+ selectedDate+
				startTime+ endTime+ capacity);
		RoomReservationVo savedReservation = roomReservationService.saveData(name, floor, roomName, selectedDate,startTime, endTime, capacity);
		return savedReservation;
	}

	// check existing room booked or not
	@PostMapping("/exist")
	public boolean checkExistingRoomReservation(@RequestParam("floor") String floor,
			@RequestParam("roomName") String roomName, @RequestParam("selectedDateString") String selectedDateString,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) throws ParseException {

		System.out.println("innnnn:::"+selectedDateString);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd");
		Date selectedDate = sdf2.parse(selectedDateString);
		int existingRoom = roomReservationService.checkAlreadyExist(floor, roomName, selectedDate, startTime, endTime);
		
		
		 System.out.println("ggggggggggggg"+existingRoom);
//		boolean flag=false;
//		if (existingRoom > 0) {
//			flag = false;
//		} else {
//			flag = true;
//		}
//		return flag;
 		 // FALSE->0-> CAN'T RESERVE ROOM 
		 //TRUE-> CAN RESERVE ROOM  
		return existingRoom == 0;
	}

	@GetMapping("/getall")
	public List<RoomReservationVo> getAllData() {
		int is_deleted=0;
		List<RoomReservationVo> all = roomReservationService.getAllData(is_deleted);
		// System.out.println(all);
		return all;
	}
	
	@GetMapping("/get/{id}")
		public RoomReservationVo getRoomReservationById(@PathVariable("id") long id) {
		return roomReservationService.getById(id);
		}
	
	// delete room reservation
	@PostMapping("/delete/{id}")
	public int deleteRoomReservationById(@PathVariable("id") long id) {
		return roomReservationService.deleteRoomReservation(id);
	}
}
