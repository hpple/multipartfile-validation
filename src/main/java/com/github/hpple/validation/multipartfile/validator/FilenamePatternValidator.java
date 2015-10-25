package com.github.hpple.validation.multipartfile.validator;

import org.springframework.web.multipart.*;
import com.github.hpple.validation.multipartfile.constraints.FilenamePattern;

import javax.validation.*;
import java.util.regex.*;

public class FilenamePatternValidator extends AbstractMultipartFileValidator
        implements ConstraintValidator<FilenamePattern, MultipartFile> {

    private Pattern pattern;

    @Override
    public void initialize(FilenamePattern annotation) {
        int flags = 0;

        for (FilenamePattern.Flag flag : annotation.flags()) {
            flags |= flag.getValue();
        }

        //noinspection MagicConstant
        this.pattern = Pattern.compile(annotation.value(), flags);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return isAbsent(file) || pattern.matcher(file.getOriginalFilename()).matches();
    }
}
