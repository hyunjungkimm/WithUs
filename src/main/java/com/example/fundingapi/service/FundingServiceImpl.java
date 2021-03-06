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

        //Page<Product> productList = productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dt, dt, pageable);
        List<Product> productList = productRepository.findAll();

        for(Product product : productList){
            System.out.println(product);
        }

        return productList;
    }

    @Override
    @Transactional
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

            //?????? ????????????
            if(fundingAmountSum == product.get().getTargetFundingAmount()){
                product.get().setFundingStatus("?????? ??????");
            }
            product.get().setTotalFundingAmount(fundingAmountSum);
            product.get().setFundingUserNumber(product.get().getFundingUserNumber()+1);
            productRepository.save(product.get());

            Product product1 = new Product();
            product1.setProductId(funding.getProduct().getProductId());

            user.setUserId(funding.getUser().getUserId());
            //????????????
            funding.setFundingAmount(funding.getFundingAmount());
            funding.setFundingDate(LocalDateTime.now());
            funding.setFundingId(funding.getFundingId());
            funding.setUser(user);
            funding.setProduct(product1);

            fundingRepository.save(funding);

            fundingResponse.setFundingStatus("?????????");


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

  /*  //Lock ?????? ?????? ??????????????? - ????????? ??? ??????
    @Override
    @Transactional
    public void lockTest(long productId) {

        // select
        Product product = productRepository.findByProductId(productId);
        int currentAmount = product.getTotalFundingAmount();
        System.out.println(currentAmount);

        // sleep -> 3~5??? ????????? ???????????? ?????????
        // sleep??? ?????? ?????? => ?????? ???????????? ????????? ???????????? ??????.
        try {
            Thread.sleep(5000);
        }  catch(Exception e) {
            e.printStackTrace();
        }
        // update
        product.setTotalFundingAmount(currentAmount+1000);
    }*/

    //Lock ?????? ?????? ??????????????? - ????????? ?????????
    @Override
    @Transactional
    public void pessimistcLockTest(long productId) {

        // select
        Product product = productRepository.findByProductId(productId);
        int currentAmount = product.getTotalFundingAmount();
        System.out.println(currentAmount);

        // sleep -> 3~5??? ????????? ???????????? ?????????
        // sleep??? ?????? ?????? => ?????? ???????????? ????????? ???????????? ??????.
        try {
            Thread.sleep(2000);
        }  catch(Exception e) {
            e.printStackTrace();
        }
        // update
        product.setTotalFundingAmount(currentAmount+1000);
    }
}
