package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FundingRepository extends CrudRepository<Funding, Long> {

    public List<Funding> findByUserId(long userId);

}
