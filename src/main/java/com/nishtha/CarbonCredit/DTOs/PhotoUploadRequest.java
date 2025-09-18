package com.nishtha.CarbonCredit.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhotoUploadRequest {
    private String projectId;

    @NotBlank
    private String url;

    private double latitude;
    private double longitude;
}

