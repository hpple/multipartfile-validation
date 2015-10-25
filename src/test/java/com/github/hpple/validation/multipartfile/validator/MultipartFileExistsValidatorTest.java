package com.github.hpple.validation.multipartfile.validator;

import com.github.hpple.validation.multipartfile.constraints.MultipartFileExists;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class MultipartFileExistsValidatorTest extends AbstractMultipartFileValidatorTest {

    @InjectMocks
    private Bean bean;

    @Test
    public void violation_WhenFileIsNull() throws Exception {
        bean.file = null;

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertViolations(violations);
    }

    @Test
    public void violation_WhenFileIsEmpty() throws Exception {
        whenFileIsEmpty();

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertViolations(violations);
    }

    @Test
    public void noViolation_WhenFileIsPresent() throws Exception {
        whenFileIsPresent();

        Set<ConstraintViolation<Bean>> violations = validate(bean);

        assertNoViolations(violations);
    }

    static class Bean {
        @MultipartFileExists
        MultipartFile file;
    }
}