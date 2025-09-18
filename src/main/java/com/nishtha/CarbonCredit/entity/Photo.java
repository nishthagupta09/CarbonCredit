package com.nishtha.CarbonCredit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {
    private String url;
    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;

    private List<VerificationResult> checks;
}
