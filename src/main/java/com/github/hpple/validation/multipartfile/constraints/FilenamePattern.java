package com.github.hpple.validation.multipartfile.constraints;

import com.github.hpple.validation.multipartfile.validator.FilenamePatternValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FilenamePatternValidator.class)
public @interface FilenamePattern {
    String message() default "{com.github.hpple.validation.multipartfile.constraints.FilenamePattern.message}";

    Flag[] flags() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value();

    enum Flag {

        UNIX_LINES(java.util.regex.Pattern.UNIX_LINES),

        CASE_INSENSITIVE(java.util.regex.Pattern.CASE_INSENSITIVE),

        COMMENTS(java.util.regex.Pattern.COMMENTS),

        MULTILINE(java.util.regex.Pattern.MULTILINE),

        DOTALL(java.util.regex.Pattern.DOTALL),

        UNICODE_CASE(java.util.regex.Pattern.UNICODE_CASE),

        CANON_EQ(java.util.regex.Pattern.CANON_EQ);

        private final int value;

        private Flag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}