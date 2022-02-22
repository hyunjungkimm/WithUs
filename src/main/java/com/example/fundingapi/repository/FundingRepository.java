package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FundingRepository extends CrudRepository<Product, Long> {
    public List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(String dateTime, String now);

}

