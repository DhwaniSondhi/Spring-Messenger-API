package org.prac.MessengerAPI.customannotations.uniqueprofile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.prac.MessengerAPI.customexceptions.ValidationError;
import org.prac.MessengerAPI.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProfileValidator implements ConstraintValidator<UniqueProfile, String> {

	@Autowired
	ProfileDao profileDao;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			profileDao.getProfileByProfileName(value);
		} catch (Exception e) {
			return true;
		}
		throw new ValidationError(context.getDefaultConstraintMessageTemplate());
	}

}
