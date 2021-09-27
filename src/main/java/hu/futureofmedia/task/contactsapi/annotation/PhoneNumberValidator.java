package hu.futureofmedia.task.contactsapi.annotation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumbers, String> {

    PhoneNumberUtil number = PhoneNumberUtil.getInstance();

    @Override
    public void initialize(PhoneNumbers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        value = value.replaceAll("[\\s()-]", "");

        try {
            number.parse(value, "HU");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
