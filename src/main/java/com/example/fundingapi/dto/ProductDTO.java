package com.example.fundingapi.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class ProductDTO{

    private long productId;
    private String title;
    private int targetFundingAmount;
    private String startDate;
    private String finishDate;
    private int totalFundingAmount;
    private String fundingStatus;

}
