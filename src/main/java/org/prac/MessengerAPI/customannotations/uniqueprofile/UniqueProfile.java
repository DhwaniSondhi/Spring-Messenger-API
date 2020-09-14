package org.prac.MessengerAPI.customannotations.uniqueprofile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Constraint(validatedBy = UniqueProfileValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueProfile {

	public String message() default "Given profile name/author is already present.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
