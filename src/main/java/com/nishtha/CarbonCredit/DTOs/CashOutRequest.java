package com.nishtha.CarbonCredit.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CashOutRequest {


    private String projectId;

    private String farmerId;

    @Pattern(regexp = "BANK|OFFICE")
    private String mode;
}

