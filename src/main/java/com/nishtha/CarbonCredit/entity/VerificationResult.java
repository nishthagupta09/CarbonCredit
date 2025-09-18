package com.nishtha.CarbonCredit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationResult {
    private String type; // AI or SAT
    private boolean passed;
    private double confidence;
}
