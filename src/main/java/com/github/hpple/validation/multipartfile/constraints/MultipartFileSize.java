package com.github.hpple.validation.multipartfile.constraints;

import com.github.hpple.validation.multipartfile.validator.MultipartFileSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultipartFileSizeValidator.class})
public @interface MultipartFileSize {

    String message() default "{com.github.hpple.validation.multipartfile.constraints.MultipartFileSize.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    long min() default 0;

    long max() default Long.MAX_VALUE;
}

