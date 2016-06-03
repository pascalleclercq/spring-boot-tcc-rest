package com.atomikos.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RoomReservation> bookRoomReservation(
			@RequestBody RoomReservation roomReservation) {
		roomReservation.setState(State.BOOKED);
		roomReservation = roomReservationRepository.save(roomReservation);
		return new ResponseEntity<RoomReservation>(roomReservation,HttpStatus.CREATED);
	}
	
    
	@RequestMapping(path = "/{reservationId}", method = RequestMethod.PUT, produces = "application/tcc" , consumes="application/tcc" )
	public ResponseEntity<Void> confirmRoomReservation(
			@PathVariable Long reservationId) {
		RoomReservation roomReservation = roomReservationRepository.getOne(reservationId);
		roomReservation.setState(State.CONFIRMED);
		roomReservationRepository.save(roomReservation);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE, produces = "application/tcc" , consumes="application/tcc")
	public ResponseEntity<Void> cancelRoomReservation(
			@PathVariable Long reservationId) {
		RoomReservation roomReservation = roomReservationRepository.getOne(reservationId);
		roomReservationRepository.delete(roomReservation.getId());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
