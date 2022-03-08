package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
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

 /*   @Override
    public void productFunding(Funding funding) {
        Optional<Product> product = productRepository.findById(funding.getProduct().getProductId());

        System.out.println(product);
        int fundingAmountSum = product.get().getTotalFundingAmount() + funding.getFundingAmount();
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

            Product product1 = new Product();
            product1.setProductId(funding.getProduct().getProductId());

            User user = new User();
            user.setUserId(funding.getUser().getUserId());
            //펀딩하기
            funding.setFundingAmount(funding.getFundingAmount());
            funding.setFundingDate(LocalDateTime.now());
            funding.setOrderId(funding.getOrderId());
            funding.setUser(user);
            funding.setProduct(product1);
            fundingRepository.save(funding);

        }

    }
*/

    @Override
    public void productFunding(long userId, long productId, FundingRequest fundingRequest) {
        Optional<Product> product = productRepository.findById(productId);

        int fundingAmountSum = product.get().getTotalFundingAmount() + fundingRequest.getFundingAmount();

        Funding funding = new Funding();

        product.get().setProductId(productId);

        User user = new User();
        user.setUserId(userId);

        funding.setUser(user);
        funding.setProduct(product.get());
        funding.setFundingAmount(fundingRequest.getFundingAmount());

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

            Product product1 = new Product();
            product1.setProductId(funding.getProduct().getProductId());

            user.setUserId(funding.getUser().getUserId());
            //펀딩하기
            funding.setFundingAmount(funding.getFundingAmount());
            funding.setFundingDate(LocalDateTime.now());
            funding.setOrderId(funding.getOrderId());
            funding.setUser(user);
            funding.setProduct(product1);
            fundingRepository.save(funding);

        }

    }

    @Override
    public List<Funding> fundingList(long orderId) {
        List<Funding> fundingList = fundingRepository.findByOrderId(orderId);

        for(Funding funding: fundingList){
            System.out.println(funding);
        }

        return fundingList;
    }
}
