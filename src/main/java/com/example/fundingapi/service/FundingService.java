package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.dto.FundingDTO;
import com.example.fundingapi.dto.MyFundingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FundingService {
    List<Product> productList();

    FundingResponse productFunding(long userId, long productId, FundingRequest fundingRequest);

    List<MyFundingDTO> fundingList(long userId);
/*

    void lockTest(long productId);
*/

    void pessimistcLockTest(long productId);

}
