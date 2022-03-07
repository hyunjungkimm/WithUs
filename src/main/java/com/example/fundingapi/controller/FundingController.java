package com.example.fundingapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.service.FundingService;

@RestController
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @GetMapping("/productList")
    @ResponseBody
    public List<Product> productList(){
        return fundingService.productList();
    }

    @PatchMapping("/productFunding/{product_id}")
    @ResponseBody
    public void productFunding(
        @RequestAttribute long userId,
        @PathVariable(name = "product_id") long productId,
        @RequestParam int fundingAmount
    ){

        Funding funding = new Funding();

        Product product = new Product();
        product.setProductId(productId);

        User user = new User();
        user.setUserId(userId);

        funding.setUser(user);
        funding.setProduct(product);
        funding.setFundingAmount(fundingAmount);
        fundingService.productFunding(funding);
    }

    @GetMapping("/fundingList")
    @ResponseBody
    public List<Funding> fundingList(long user_id){
        return fundingService.fundingList(user_id);
    }
}
