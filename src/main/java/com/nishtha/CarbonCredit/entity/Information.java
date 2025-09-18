package com.nishtha.CarbonCredit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "information")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {

    @Id
    private String id;

    private String farmerId;
    private String farmerEmail;
    private String crop;
    private String method;
    private double area;
    private String state;
    private String status; // ACTIVE / PENDING_VERIFICATION / APPROVED / REJECTED
    private double estimatedCredits;
    private double finalCredits;

    @Builder .Default
    private List<Photo> photos = new ArrayList<>();
}

