package com.example.fundingapi.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
public class Product {
    @Id
    @Column(name ="product_id")
    @GeneratedValue
    private long productId;
    private String title;
    @Column(name="target_funding_amount")
    private int targetFundingAmount;
    @Column(name="start_date")
    private String startDate;
    @Column(name="finish_date")
    private String finishDate;
    @Column(name="total_funding_amount")
    private int totalFundingAmount;
    @Column(name="funding_status")
    private String fundingStatus;

}
