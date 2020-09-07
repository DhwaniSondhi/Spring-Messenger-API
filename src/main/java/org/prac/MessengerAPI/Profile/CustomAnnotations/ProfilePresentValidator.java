package org.prac.MessengerAPI.Profile.CustomAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.prac.MessengerAPI.Profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfilePresentValidator implements ConstraintValidator<ProfilePresent, String> {

	@Autowired
	private ProfileDao profileDao;

	@Override
	public boolean isValid(String author, ConstraintValidatorContext context) {
		try {
			profileDao.getProfileByProfileName(author);
			return true;
		} catch (Exception e) {
			throw new ProfileNotPresentException(context.getDefaultConstraintMessageTemplate());
		}
	}
}
