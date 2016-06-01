package com.atomikos.jaxrs;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Flight() {
	
	}
	
	public Flight(Date departure, String departureAirport, Date landing,
			String landingAirort, State state) {
		super();
		this.departure = departure;
		this.departureAirport = departureAirport;
		this.landing = landing;
		this.landingAirort = landingAirort;
		this.state = state;
	}
	
	private Date departure;
	private String departureAirport;
	private Date landing;
	private String landingAirort;
	
	private State state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDeparture() {
		return departure;
	}



	public void setDeparture(Date departure) {
		this.departure = departure;
	}



	public String getDepartureAirport() {
		return departureAirport;
	}



	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}



	public Date getLanding() {
		return landing;
	}



	public void setLanding(Date landing) {
		this.landing = landing;
	}



	public String getLandingAirort() {
		return landingAirort;
	}



	public void setLandingAirort(String landingAirort) {
		this.landingAirort = landingAirort;
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

}
