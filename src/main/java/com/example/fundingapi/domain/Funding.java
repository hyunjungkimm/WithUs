package com.example.fundingapi.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name="funding")
@Entity
public class Funding {
    @Id
    @GeneratedValue
    private long orderId;
    private int fundingAmount;
    @CreatedDate
    private LocalDateTime fundingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}

