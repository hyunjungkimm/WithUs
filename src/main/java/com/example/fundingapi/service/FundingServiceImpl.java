package com.example.fundingapi.service;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.repository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FundingServiceImpl implements FundingService{

    @Autowired
    FundingRepository fundingRepository;

    @Override
    public List<Product> getproductList() {
        String dateTime = LocalDateTime.now() +"";
        String now = dateTime;
        List<Product> productList = fundingRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dateTime, now);

        for(Product product : productList){
            System.out.println(product);
        }

        return productList;
    }
}
