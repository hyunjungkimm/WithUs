package com.example.fundingapi.controller;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.example.fundingapi.service.FundingService;

import java.time.LocalDateTime;

@RestController
public class ProductController {

	@Autowired
	private FundingService fundingService;

	private final ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
/*

	@PatchMapping("/v1/products/{product_id}/funding")
	public void lockTest(
		@PathVariable("product_id") Long productId
	) {
		try{
			fundingService.lockTest(productId);
		} catch(ObjectOptimisticLockingFailureException e){
			System.out.println("재시도");
			lockTest(productId);
		}
	}
*/

	@PatchMapping("/v2/products/{product_id}/funding")
	public void pessimistcLockTest(
		@PathVariable("product_id") Long productId
	) {
		fundingService.pessimistcLockTest(productId);

	}

	@GetMapping("/v1/products/list")
	public Page<Product> getAllProducts(){
		PageRequest pageRequest = PageRequest.of(0,4);
		return productRepository.findAll(pageRequest);
	}

	@GetMapping("/v2/products/list")
	public Page<Product> getAllProductWithPageByQueryMethod(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		LocalDateTime dt = LocalDateTime.now();
		return productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dt, dt, pageRequest);
	}
}
