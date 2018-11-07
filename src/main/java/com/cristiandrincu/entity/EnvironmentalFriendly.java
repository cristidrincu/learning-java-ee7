package com.cristiandrincu.entity;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD, ANNOTATION_TYPE, PARAMETER })
@Constraint(validatedBy = EnvironmentalFriendlyValidator.class)
@Documented
public @interface EnvironmentalFriendly {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
