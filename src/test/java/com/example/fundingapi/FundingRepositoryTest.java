package com.example.fundingapi;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.repository.FundingRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundingRepositoryTest {

    @Autowired
    FundingRepository fundingRepository;


    @Test
    @DisplayName("나의 펀딩 상품 조회하기 API")
    public void findByUserId(){
        long userId = 1;
        List<Funding> list = fundingRepository.findByUserUserId(userId);
        for(Funding funding : list){
            System.out.println(funding.toString());
        }
    }


    @Test
    public void Resultest(){
        FundingRequest request = new FundingRequest(1000);
        System.out.println(request);
    }

}
