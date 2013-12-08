package com.rentit.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4073824348278732650L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
