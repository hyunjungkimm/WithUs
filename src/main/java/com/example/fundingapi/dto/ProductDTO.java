package com.example.fundingapi.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class ProductDTO{

    private long productId;
    private String title;
    private int targetFundingAmount;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private int totalFundingAmount;
    private String fundingStatus;

}
