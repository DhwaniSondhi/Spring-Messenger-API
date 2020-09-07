package org.prac.MessengerAPI.Profile.CustomAnnotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ProfilePresentValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ProfilePresent {

	public String message() default "";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
