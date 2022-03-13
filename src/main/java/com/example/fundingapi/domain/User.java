package com.example.fundingapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name ="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    @Column(name="name")
    private String name;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Funding> funding = new ArrayList<>();

}
