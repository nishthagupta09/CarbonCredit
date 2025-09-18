package com.nishtha.CarbonCredit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayoutRequest {
    @Id
    private String id;

    private String projectId;
    private String farmerId;
    private String mode; // BANK / OFFICE
    private LocalDateTime requestDate;
    private String status; // PENDING / COMPLETED
}

