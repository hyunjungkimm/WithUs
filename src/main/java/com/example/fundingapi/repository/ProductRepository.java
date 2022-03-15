package com.example.fundingapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fundingapi.domain.Product;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface ProductRepository extends JpaRepository<Product, Long> {
/*    List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(String dateTime, String now);*/
	List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(LocalDateTime dateTime, LocalDateTime now);
/*

	@Lock(LockModeType.OPTIMISTIC)
	Product findByProductId(long productId);
*/

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Product findByProductId(long productId);
}

