package com.nishtha.CarbonCredit.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DecisionRequest {

    @NotBlank
    private String projectId;

    @Pattern(regexp = "APPROVED|REJECTED")
    private String decision; // APPROVED / REJECTED
}

