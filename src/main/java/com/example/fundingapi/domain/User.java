package com.example.fundingapi.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Funding> funding = new ArrayList<>();

}
