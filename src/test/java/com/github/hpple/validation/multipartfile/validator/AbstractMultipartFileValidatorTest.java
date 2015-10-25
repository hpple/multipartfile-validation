package com.github.hpple.validation.multipartfile.validator;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
abstract class AbstractMultipartFileValidatorTest {

    @Mock
    MultipartFile fileMock;

    <T> Set<ConstraintViolation<T>> validate(T object) {
        return Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(object);
    }

    void whenFileIsEmpty() {
        when(fileMock.isEmpty()).thenReturn(true);
        when(fileMock.getOriginalFilename()).thenReturn("");
    }

    void whenFileIsPresent() {
        whenFileWithOriginalFilenameIsPresent("foo.bar");
    }

    void whenFileWithOriginalFilenameIsPresent(String name) {
        when(fileMock.isEmpty()).thenReturn(true);
        when(fileMock.getOriginalFilename()).thenReturn(name);
    }

    static void assertViolations(Set<? extends ConstraintViolation<?>> violations) {
        assertFalse("At least one violation expected!", violations.isEmpty());
    }

    static void assertNoViolations(Set<? extends ConstraintViolation<?>> violations) {
        assertTrue("No violations expected!", violations.isEmpty());
    }
}
