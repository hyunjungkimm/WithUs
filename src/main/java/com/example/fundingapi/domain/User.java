package com.example.fundingapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name ="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    @Column(name="name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Funding> funding = new ArrayList<>();
}
