package com.example.fundingapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.fundingapi.service.FundingService;

@RestController
public class ProductController {

	@Autowired
	private FundingService fundingService;

	@PatchMapping("/v1/products/{product_id}/funding")
	public void lockTest(
		@PathVariable("product_id") Long productId
	) {
		fundingService.lockTest(productId);
	}
}
