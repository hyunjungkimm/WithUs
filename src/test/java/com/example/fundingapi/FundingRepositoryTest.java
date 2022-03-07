package com.example.fundingapi;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.repository.FundingRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundingRepositoryTest {

    @Autowired
    FundingRepository fundingRepository;
/*

    @Test
    @DisplayName("나의 펀딩 상품 조회하기 API")
    public void findByUserId(){
        long orderdId = 1;
        List<Funding> list = fundingRepository.findByOrderId(orderdId);
        for(Funding funding : list){
            System.out.println(funding.toString());
        }
    }
*/

}
