package com.example.fundingapi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="product")
@Entity
public class Product implements Serializable {
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
    @Column(name="funding_user_number")
    private int fundingUserNumber = 0 ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Funding> funding = new ArrayList<>();

}

