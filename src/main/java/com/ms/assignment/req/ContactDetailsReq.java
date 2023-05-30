package com.ms.assignment.req;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDetailsReq {
    @NotEmpty(message = "Full Name is required")
    private String fullName;
    @Email
    private String emailAddress;


    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "\\d+", message = "Mobile number must be numeric")
    private String mobileNumber;

    @NotEmpty(message = "Country code is required")
    @Pattern(regexp = "\\+\\d{1,3}", message = "Country code must start with '+' followed by 1-3 digits")
    private String countryCode;
}
