package com.example.fundingapi.domain;

import com.example.fundingapi.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name="PRODUCT")
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
    private LocalDateTime startDate;
    @Column(name="finish_date")
    private LocalDateTime finishDate;
    @Column(name="total_funding_amount")
    private int totalFundingAmount;
    @Column(name="funding_status")
    private String fundingStatus;
    @Column(name="funding_user_number")
    private int fundingUserNumber = 0 ;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Funding> funding = new ArrayList<>();

    @Version
    private Integer version;

}

