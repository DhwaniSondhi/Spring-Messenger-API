package org.prac.MessengerAPI.customexceptions;

public class ValidationError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationError(String message) {
		super(message);
	}
}
