package com.example.fundingapi.controller;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.service.FundingService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FundingController {
    @Autowired
    private FundingService fundingService;

    @GetMapping("/getProductList")
    @ResponseBody
    public List<Product> getProductList(){
        return fundingService.getproductList();
    }

}
