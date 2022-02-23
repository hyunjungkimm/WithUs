package com.example.fundingapi.service;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.dto.FundingDTO;
import com.example.fundingapi.repository.FundingRepository;
import com.example.fundingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FundingServiceImpl implements FundingService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FundingRepository fundingRepository;

    @Override
    public List<Product> productList() {
        String dateTime = LocalDateTime.now() +"";
        String now = dateTime;
        List<Product> productList = productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dateTime, now);

        for(Product product : productList){
            System.out.println(product);
        }

        return productList;
    }

    @Override
    public void productFunding(FundingDTO fundingDTO) {
        Optional<Product> product = productRepository.findById(fundingDTO.getProduct_id());

        System.out.println(product);
        int fundingAmountSum = product.get().getTotalFundingAmount() + fundingDTO.getFundingAmount();
        System.out.println(fundingAmountSum);
        if(fundingAmountSum > product.get().getTargetFundingAmount()){
            System.out.println("sold-out");
        }else{
            //제품 업데이트
            if(fundingAmountSum == product.get().getTargetFundingAmount()){
                product.get().setFundingStatus("모집 완료");
            }
            product.get().setTotalFundingAmount(fundingAmountSum);
            product.get().setFundingUserNumber(product.get().getFundingUserNumber()+1);
            productRepository.save(product.get());

            //펀딩하기
            Funding funding = new Funding();
            funding.setFundingAmount(fundingDTO.getFundingAmount());
            funding.setFundingDate(LocalDateTime.now());
            funding.setOrderId(fundingDTO.getOrderId());
            funding.setUserId(fundingDTO.getUserId());
            funding.setProductId(fundingDTO.getProduct_id());
            fundingRepository.save(funding);
        }

    }

    @Override
    public List<Funding> fundingList(long user_id) {
        List<Funding> fundingList = fundingRepository.findByUserId(user_id);

        for(Funding funding: fundingList){
            System.out.println(funding);
        }

        return fundingList;
    }
}
