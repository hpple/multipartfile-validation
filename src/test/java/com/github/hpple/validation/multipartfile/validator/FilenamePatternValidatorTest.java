package com.github.hpple.validation.multipartfile.validator;

import com.github.hpple.validation.multipartfile.constraints.FilenamePattern;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class FilenamePatternValidatorTest extends AbstractMultipartFileValidatorTest {

    @InjectMocks
    private DocxBean docxBean;

    @InjectMocks
    private IgnoreCaseDocxBean ignoreCaseDocxBean;

    @Test
    public void noViolation_WhenNoFilePresent() {
        docxBean.file = null;

        Set<ConstraintViolation<DocxBean>> violations = validate(docxBean);

        assertNoViolations(violations);
    }

    @Test
    public void noViolation_WhenFilenameMatches() {
        whenFileWithOriginalFilenameIsPresent("mockito.docx");

        Set<ConstraintViolation<DocxBean>> violations = validate(docxBean);

        assertNoViolations(violations);
    }

    @Test
    public void noViolation_WhenFilenameMatchesIgnoreCase() {
        whenFileWithOriginalFilenameIsPresent("mockito.dOcX");

        Set<ConstraintViolation<IgnoreCaseDocxBean>> violations = validate(ignoreCaseDocxBean);

        assertNoViolations(violations);
    }

    @Test
    public void violation_WhenFilenameDoesNotMatch() {
        whenFileWithOriginalFilenameIsPresent("mockito.mock");

        Set<ConstraintViolation<DocxBean>> violations = validate(docxBean);

        assertViolations(violations);
    }

    public static class DocxBean {
        @FilenamePattern(".+\\.docx")
        MultipartFile file;
    }

    public static class IgnoreCaseDocxBean {
        @FilenamePattern(flags = FilenamePattern.Flag.CASE_INSENSITIVE, value = ".+\\.docx")
        MultipartFile file;
    }
}
