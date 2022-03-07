package com.example.fundingapi.controller;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.dto.FundingDTO;
import com.example.fundingapi.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @GetMapping("/productList")
    @ResponseBody
    public List<Product> productList(){
        return fundingService.productList();
    }

    //@PatchMapping("/productFunding")
    @PatchMapping("/productFunding/{product_id}")
    @ResponseBody
    //public void productFunding(@RequestAttribute long userId, @RequestParam long productId, @RequestParam int fundingAmount){
    public void productFunding(@RequestAttribute long userId, @PathVariable(name = "product_id") long productId, @RequestParam int fundingAmount){
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
