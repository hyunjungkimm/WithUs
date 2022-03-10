package com.example.fundingapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyFundingDTO {
    private long productId;
    private String title;
    private int totalFundingAmount;
    private int fundingAmount;
    private LocalDateTime fundingDate;
}
