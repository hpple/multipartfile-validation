package com.github.hpple.validation.multipartfile.validator;

import com.github.hpple.validation.multipartfile.constraints.MultipartFileSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileSizeValidator extends AbstractMultipartFileValidator
        implements ConstraintValidator<MultipartFileSize, MultipartFile> {

    private long min;
    private long max;

    @Override
    public void initialize(MultipartFileSize annotation) {
        min = annotation.min();
        max = annotation.max();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return isAbsent(file) || min <= file.getSize() && file.getSize() <= max;
    }
}
