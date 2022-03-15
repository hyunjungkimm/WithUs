package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.dto.MyFundingDTO;
import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.service.funding.FundingServiceException;
import com.example.fundingapi.repository.FundingRepository;
import com.example.fundingapi.repository.ProductRepository;
import com.example.fundingapi.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class FundingServiceImpl implements FundingService{

    private final ProductRepository productRepository;

    private final FundingRepository fundingRepository;

    public FundingServiceImpl(ProductRepository productRepository, FundingRepository fundingRepository) {
        this.productRepository = productRepository;
        this.fundingRepository = fundingRepository;
    }

    @Override
    public List<Product> productList() {
        LocalDateTime dt = LocalDateTime.now();

        List<Product> productList = productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dt, dt);

        for(Product product : productList){
            System.out.println(product);
        }

        return productList;
    }

    @Override
    public FundingResponse productFunding(long userId, long productId, FundingRequest fundingRequest) {
        Optional<Product> product = productRepository.findById(productId);

        int fundingAmountSum = product.get().getTotalFundingAmount() + fundingRequest.getFundingAmount();

        Funding funding = new Funding();

        product.get().setProductId(productId);

        User user = new User();
        user.setUserId(userId);

        funding.setUser(user);
        funding.setProduct(product.get());
        funding.setFundingAmount(fundingRequest.getFundingAmount());

        FundingResponse fundingResponse = new FundingResponse();

        if(fundingAmountSum > product.get().getTargetFundingAmount()){
            System.out.println("sold-out");
            throw new FundingServiceException(ErrorCode.SOLD_OUT);
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
            funding.setFundingId(funding.getFundingId());
            funding.setUser(user);
            funding.setProduct(product1);
            fundingRepository.save(funding);

            fundingResponse.setFundingStatus("모집중");


        }
        return fundingResponse;
    }

    @Override
    public List<MyFundingDTO> fundingList(long userId) {

        List<Funding> fundingList = fundingRepository.findByUserUserId(userId);
        List<MyFundingDTO> myFundingList = new ArrayList<>();

        for(Funding funding: fundingList){
            MyFundingDTO myFundingDTO = new MyFundingDTO();
            myFundingDTO.setProductId(funding.getProduct().getProductId());
            myFundingDTO.setTitle(funding.getProduct().getTitle());
            myFundingDTO.setTotalFundingAmount(funding.getProduct().getTotalFundingAmount());
            myFundingDTO.setFundingAmount(funding.getFundingAmount());
            myFundingDTO.setFundingDate(funding.getFundingDate());

            myFundingList.add(myFundingDTO);
        }
        return myFundingList;
    }

    //Lock 걸지 않고 시도해보기
    @Override
    @Transactional
    public void lockTest(long productId) {

        // select
        Product product = productRepository.findByProductId(productId);
        int currentAmount = product.getTotalFundingAmount();
        System.out.println(currentAmount);

        // sleep -> 3~5초 인터넷 찾아보고 써보기
        // sleep을 주는 의미 => 다른 비즈니스 코드가 수행되고 있음.
        try {
            Thread.sleep(5000);
        }  catch(Exception e) {
            e.printStackTrace();
        }
        // update
        product.setTotalFundingAmount(currentAmount+1000);
    }
}
