package org.prac.MessengerAPI.customannotations.profilepresent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.prac.MessengerAPI.customexceptions.ValidationError;
import org.prac.MessengerAPI.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfilePresentValidator implements ConstraintValidator<ProfilePresent, String> {

	@Autowired
	private ProfileDao profileDao;

	@Override
	public boolean isValid(String author, ConstraintValidatorContext context) {
		try {
			profileDao.getProfileByProfileName(author);
			return true;
		} catch (Exception e) {
			throw new ValidationError(context.getDefaultConstraintMessageTemplate());
		}
	}
}
