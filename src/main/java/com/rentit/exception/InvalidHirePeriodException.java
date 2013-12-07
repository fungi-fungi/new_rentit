package com.rentit.exception;

@SuppressWarnings("serial")
public class InvalidHirePeriodException extends Exception {
	public InvalidHirePeriodException(String message) {
		super(message);
	}
}