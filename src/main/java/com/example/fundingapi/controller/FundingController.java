package com.example.fundingapi.controller;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.dto.FundingDTO;
import com.example.fundingapi.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @GetMapping("/productList")
    @ResponseBody
    public List<Product> productList(){
        return fundingService.productList();
    }

    @PatchMapping("/productFunding")
    @ResponseBody
    public void productFunding(@RequestAttribute long user_id, long product_id, int funding_amount){
        FundingDTO fundingDTO = new FundingDTO();
        fundingDTO.setUserId(user_id);
        fundingDTO.setProduct_id(product_id);
        fundingDTO.setFundingAmount(funding_amount);
        fundingService.productFunding(fundingDTO);
    }

    @GetMapping("/fundingList")
    @ResponseBody
    public List<Funding> fundingList(long user_id){
        return fundingService.fundingList(user_id);
    }
}
