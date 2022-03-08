package com.example.fundingapi.controller;

import java.util.List;

import com.example.fundingapi.data.FundingRequest;
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

    @PatchMapping(value = "/productFunding/{product_id}")
    @ResponseBody
    public void productFunding(
        @RequestAttribute long userId,
        @PathVariable(name = "product_id") long productId,
        @RequestBody @NotNull FundingRequest fundingRequest
    ){

        Funding funding = new Funding();

        Product product = new Product();
        product.setProductId(productId);

        User user = new User();
        user.setUserId(userId);

        funding.setUser(user);
        funding.setProduct(product);
        funding.setFundingAmount(fundingRequest.getFundingAmount());
        fundingService.productFunding(funding);
    }

    @GetMapping("/fundingList")
    @ResponseBody
    public List<Funding> fundingList(long user_id){
        return fundingService.fundingList(user_id);
    }
}
