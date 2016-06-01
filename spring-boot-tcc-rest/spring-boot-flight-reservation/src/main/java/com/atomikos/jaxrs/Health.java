package com.atomikos.jaxrs;

public class Health {

	private String message;

	public Health() {
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Health(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
