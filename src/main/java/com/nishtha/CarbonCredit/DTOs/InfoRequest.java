package com.nishtha.CarbonCredit.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class InfoRequest {

    private String farmerId;

    @NotBlank
    private String crop;

    @NotBlank
    private String method;

    @NotNull
    @Positive
    private double area;

    @NotBlank
    private String state;

    private String status;
}

