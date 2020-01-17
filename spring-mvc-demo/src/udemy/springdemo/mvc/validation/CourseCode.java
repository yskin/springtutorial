package udemy.springdemo.mvc.validation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { CourseCodeConstraintValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	// define default course code
	String value() default "CRS";

	// define default error message
	String message() default "must start with CRS";

	// define default groups
	Class<?>[] groups() default {};

	// define default payloads
	Class<? extends Payload>[] payload() default {};

}
