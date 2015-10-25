package com.github.hpple.validation.multipartfile.constraints;


import com.github.hpple.validation.multipartfile.validator.MultipartFileExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileExistsValidator.class)
public @interface MultipartFileExists {
    String message() default "{com.github.hpple.validation.multipartfile.constraints.MultipartFileExists.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}