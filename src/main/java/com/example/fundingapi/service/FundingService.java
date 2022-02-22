package com.example.fundingapi.service;

import com.example.fundingapi.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FundingService {
    public List<Product> getproductList();
}
