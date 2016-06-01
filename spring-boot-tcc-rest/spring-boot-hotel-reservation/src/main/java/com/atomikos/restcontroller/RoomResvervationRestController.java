package com.atomikos.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atomikos.restcontroller.RoomReservation.State;

@RestController
@RequestMapping("/hotel")
public class RoomResvervationRestController {

	@Autowired
	private RoomReservationRepository roomReservationRepository;

	@RequestMapping(path = "/health", method = RequestMethod.GET, produces = "application/json")
	public Health health() {
		return new Health("Spring Rest: Up and Running!");
	}

	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RoomReservation> bookRoomReservation(
			@RequestBody RoomReservation roomReservation) {
		roomReservation.setState(State.BOOKED);
		roomReservation = roomReservationRepository.save(roomReservation);
		return new ResponseEntity<RoomReservation>(roomReservation,HttpStatus.CREATED);
	}

	@RequestMapping(path = "/confirm/{reservationId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RoomReservation> confirmRoomReservation(
			Long reservationId) {
		RoomReservation roomReservation = roomReservationRepository.getOne(reservationId);
		roomReservation.setState(State.CONFIRMED);
		return new ResponseEntity<RoomReservation>(HttpStatus.OK);
	}

	@RequestMapping(path = "/cancel/{reservationId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<RoomReservation> cancelRoomReservation(
			Long reservationId) {
		RoomReservation roomReservation = roomReservationRepository.getOne(reservationId);
		roomReservation.setState(State.CANCELLED);
		return new ResponseEntity<RoomReservation>(HttpStatus.OK);
	}

}
