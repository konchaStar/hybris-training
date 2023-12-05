package concerttours.constraints;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotLoremIpsumValidator implements ConstraintValidator<NotLoremIpsum, String> {
    private static final String LOREM_IPSUM = "lorem ipsum";

    @Override
    public void initialize(NotLoremIpsum constraintAnnotation) {
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isBlank(str) || !str.toLowerCase().startsWith(LOREM_IPSUM);
    }
}
