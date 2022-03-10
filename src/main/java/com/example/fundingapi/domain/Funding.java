package com.example.fundingapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name="funding")
@Entity
public class Funding {
    @Id
    @Column(name ="funding_id")
    @GeneratedValue
    private long fundingId;
    @Column(name ="funding_amount")
    private int fundingAmount;
    @CreatedDate
    @Column(name ="funding_date")
    private LocalDateTime fundingDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

}

