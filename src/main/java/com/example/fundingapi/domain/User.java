package com.example.fundingapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long userId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Funding> funding;
}
