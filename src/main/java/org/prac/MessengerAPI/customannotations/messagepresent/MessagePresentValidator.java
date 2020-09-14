package org.prac.MessengerAPI.customannotations.messagepresent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.prac.MessengerAPI.customexceptions.ValidationError;
import org.prac.MessengerAPI.message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;

public class MessagePresentValidator implements ConstraintValidator<MessagePresent, Long> {

	@Autowired
	MessageDao messageDao;

	@Override
	public boolean isValid(Long id, ConstraintValidatorContext context) {
		try {
			messageDao.getMessageById(id);
			return true;
		} catch (Exception e) {
			throw new ValidationError(context.getDefaultConstraintMessageTemplate());
		}
	}

}
