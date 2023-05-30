package com.ms.assignment.req;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

 class ContactDetailsReqTest {

    @Mock
    private Validator validator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void testFullNameIsRequired() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .emailAddress("mkm@mymail.com")
                .mobileNumber("9874563210")
                .countryCode("+977")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("Full Name is required", violation.getMessage());
    }

    @Test
    void testEmailAddressIsValid() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .fullName("Manoj Kumar")
                .emailAddress("invalid-email")
                .mobileNumber("9874563210")
                .countryCode("+977")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("must be a well-formed email address", violation.getMessage());
    }

    @Test
    void testMobileNumberIsRequired() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .fullName("Manoj Kumar")
                .emailAddress("mkm@mymail.com")
                .countryCode("+977")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("Mobile number is required", violation.getMessage());
    }

    @Test
    void testMobileNumberMustBeNumeric() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .fullName("Manoj Kumar")
                .emailAddress("mkm@mymail.com")
                .mobileNumber("invalid-number")
                .countryCode("+977")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("Mobile number must be numeric", violation.getMessage());
    }

    @Test
    void testCountryCodeIsRequired() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .fullName("Manoj Kumar")
                .emailAddress("mkm@mymail.com")
                .mobileNumber("9874563210")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("Country code is required", violation.getMessage());
    }

    @Test
    void testCountryCodeFormat() {
        ContactDetailsReq contactDetailsReq = ContactDetailsReq.builder()
                .fullName("Manoj Kumar")
                .emailAddress("mkm@mymail.com")
                .mobileNumber("9874563210")
                .countryCode("977")
                .build();

        Set<ConstraintViolation<ContactDetailsReq>> violations = validator.validate(contactDetailsReq);
        Assertions.assertEquals(1, violations.size());
        ConstraintViolation<ContactDetailsReq> violation = violations.iterator().next();
        Assertions.assertEquals("Country code must start with '+' followed by 1-3 digits", violation.getMessage());
    }
}
