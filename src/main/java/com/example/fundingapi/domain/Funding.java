package com.example.fundingapi.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
public class Funding {
    @Id
    @Column(name ="order_id")
    @GeneratedValue
    private long orderId;
    @Column(name ="user_id")
    private long userId;
    @Column(name ="funding_amount")
    private int fundingAmount;
    @CreatedDate
    @Column(name ="funding_date")
    private LocalDateTime fundingDate;
    @Column(name ="product_id")
    private long productId;

}

