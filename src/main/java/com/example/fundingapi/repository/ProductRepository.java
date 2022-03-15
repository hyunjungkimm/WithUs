package com.example.fundingapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fundingapi.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
/*    List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(String dateTime, String now);*/
	List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(LocalDateTime dateTime, LocalDateTime now);

	Product findByProductId(long productId);

}

