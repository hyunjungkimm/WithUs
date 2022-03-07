package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    List<Funding> findByOrderId(long orderId);

}
