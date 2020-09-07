package org.prac.MessengerAPI.Profile.CustomAnnotations;

public class ProfileNotPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProfileNotPresentException(String message) {
		super(message);
	}
}
