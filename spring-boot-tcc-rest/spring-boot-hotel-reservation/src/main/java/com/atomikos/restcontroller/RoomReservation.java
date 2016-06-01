package com.atomikos.restcontroller;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class RoomReservation {
	@Id
	@GeneratedValue
	private Long id;
	
	private Date arrival;
	
	private Date departure;

	private String roomNumber;
	
	private Integer numberOfPerson;
	
	private State state;
	
	public RoomReservation() {
	
	}

	public RoomReservation(Date arrival, Date departure, String roomNumber,
			Integer numberOfPerson) {
		super();
		this.arrival = arrival;
		this.departure = departure;
		this.roomNumber = roomNumber;
		this.numberOfPerson = numberOfPerson;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getNumberOfPerson() {
		return numberOfPerson;
	}

	public void setNumberOfPerson(Integer numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public enum State {
		BOOKED, CONFIRMED, CANCELLED;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
