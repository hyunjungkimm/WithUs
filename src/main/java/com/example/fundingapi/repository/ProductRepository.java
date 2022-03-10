package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
/*    List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(String dateTime, String now);*/
List<Product> findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(LocalDateTime dateTime, LocalDateTime now);

}

