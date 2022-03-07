package com.example.fundingapi.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Table(name="funding")
@Entity
public class Funding {
    @Id
    @Column(name ="order_id")
    @GeneratedValue
    private long orderId;
    @Column(name ="funding_amount")
    private int fundingAmount;
    @CreatedDate
    @Column(name ="funding_date")
    private LocalDateTime fundingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}

