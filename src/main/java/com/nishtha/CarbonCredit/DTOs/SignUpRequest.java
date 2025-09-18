package com.nishtha.CarbonCredit.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    private String role;

    @NotBlank
    private String aadhar;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;
}
