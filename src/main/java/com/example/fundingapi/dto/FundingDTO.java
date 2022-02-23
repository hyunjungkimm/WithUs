package com.example.fundingapi.dto;

import com.example.fundingapi.domain.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundingDTO {

    private long orderId;
    private long userId;
    private int fundingAmount;
    private LocalDateTime fundingDate;
    private long product_id;

}

