package org.prac.MessengerAPI.customannotations.messagepresent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Constraint(validatedBy = MessagePresentValidator.class)

public @interface MessagePresent {

	public String message() default "No message id given for the comment is present in the database.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
