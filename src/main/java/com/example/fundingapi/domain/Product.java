package com.example.fundingapi.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name="product")
@Entity
public class Product {
    @Id
    @GeneratedValue
    private long productId;
    private String title;
    private int targetFundingAmount;
    private String startDate;
    private String finishDate;
    private int totalFundingAmount;
    private String fundingStatus;
    private int fundingUserNumber = 0 ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Funding> funding = new ArrayList<>();

}

