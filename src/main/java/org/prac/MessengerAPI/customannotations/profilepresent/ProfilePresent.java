package org.prac.MessengerAPI.customannotations.profilepresent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ProfilePresentValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Documented
public @interface ProfilePresent {

	public String message() default "No profile name/author given for the message is present in the database.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
