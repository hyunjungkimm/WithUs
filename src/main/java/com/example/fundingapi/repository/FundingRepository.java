package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Funding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    List<Funding> findByUserUserId(long userId);


}
