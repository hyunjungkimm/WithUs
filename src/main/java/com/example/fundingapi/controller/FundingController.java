package com.example.fundingapi.controller;

import java.util.List;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.dto.MyFundingDTO;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.service.FundingService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class FundingController {

    private final FundingService fundingService;

    public FundingController(FundingService fundingService) {
        this.fundingService = fundingService;
    }

    @GetMapping("/productList")
    @ResponseBody
    public List<Product> productList(){
        return fundingService.productList();
    }


    @PostMapping(value = "/products/{product_id}/funding")
    @ResponseBody
    public FundingResponse productFunding(
        @RequestAttribute @NotNull long userId,
        @PathVariable(name = "product_id") long productId,
        @RequestBody @Valid FundingRequest fundingRequest
    ){
        return fundingService.productFunding(userId, productId, fundingRequest);
    }

    @GetMapping("/fundingList")
    public List<MyFundingDTO> fundingList(@RequestAttribute long userId){
        return fundingService.fundingList(userId);
    }
}
