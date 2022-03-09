package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.dto.FundingDTO;

import java.util.List;

public interface FundingService {
    public List<Product> productList();

    public FundingResponse productFunding(long userId, long productId, FundingRequest fundingRequest);

    public List<Funding> fundingList(long funding_Id);

}
