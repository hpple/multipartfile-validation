package com.github.hpple.validation.multipartfile.validator;

import com.github.hpple.validation.multipartfile.constraints.MultipartFileSize;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class MultipartFileSizeValidatorTest extends AbstractMultipartFileValidatorTest {

    @InjectMocks
    private From10To20Bean bean;

    @Test
    public void noViolation_WhenNoFilePresent() {
        bean.file = null;

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertNoViolations(violations);
    }

    @Test
    public void violation_WhenFileIsEmpty() throws Exception {
        whenFileIsEmpty();

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertNoViolations(violations);
    }

    @Test
    public void noViolation_WhenFileSizeInRange() {
        whenFileIsPresent();
        when(fileMock.getSize()).thenReturn(14L);

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void violation_WhenFileSizeOutOfRange() {
        whenFileIsPresent();
        when(fileMock.getSize()).thenReturn(404L);

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertViolations(violations);
    }

    @Test
    public void noViolation_WhenFileSizeEqualsToMin() {
        whenFileIsPresent();
        when(fileMock.getSize()).thenReturn(10L);

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertNoViolations(violations);
    }

    @Test
    public void noViolation_WhenFileSizeEqualsToMax() {
        whenFileIsPresent();
        when(fileMock.getSize()).thenReturn(20L);

        Set<ConstraintViolation<From10To20Bean>> violations = validate(bean);

        assertNoViolations(violations);
    }

    private static class From10To20Bean {
        @MultipartFileSize(min = 10, max = 20)
        MultipartFile file;
    }
}
