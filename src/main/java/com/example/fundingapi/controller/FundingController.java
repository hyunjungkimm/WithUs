package com.example.fundingapi.controller;

import java.util.List;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.service.FundingService;

import javax.validation.constraints.NotNull;

@RestController
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @GetMapping("/productList")
    @ResponseBody
    public List<Product> productList(){
        return fundingService.productList();
    }

    @PostMapping(value = "/products/{product_id}/funding")
    @ResponseBody
    public FundingResponse productFunding(
        @RequestAttribute long userId,
        @PathVariable(name = "product_id") long productId,
        @RequestBody @NotNull FundingRequest fundingRequest
    ){
        return fundingService.productFunding(userId, productId, fundingRequest);
    }

    @GetMapping("/fundingList")
    @ResponseBody
    public List<Funding> fundingList(long user_id){
        return fundingService.fundingList(user_id);
    }
}
