package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.dto.MyFundingDTO;
import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.entity.user.UserNotFoundException;
import com.example.fundingapi.exception.service.funding.FundingServiceException;
import com.example.fundingapi.repository.FundingRepository;
import com.example.fundingapi.repository.ProductRepository;
import com.example.fundingapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FundingServiceTest {

    @InjectMocks
    private FundingServiceImpl fundingService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private FundingRepository fundingRepository;


    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);//이 클래스  - this
    }

    @Test
    @DisplayName("sold out 테스트")
    public void test1() {

        Long userId = 1L;
        Long productId = 1002L;
        FundingRequest fundingRequest = new FundingRequest(500000);

        Product product = Product.builder()
            .productId(productId)
            .title("환절기 토탈케어 펀딩")
            .targetFundingAmount(500000)
            .startDate(LocalDateTime.now().minusMonths(1))
            .finishDate(LocalDateTime.now().plusMonths(1))
            .fundingUserNumber(10)
            .fundingStatus("모집중")
            .totalFundingAmount(250000)
            .build();

        when(
            productRepository.findById(productId)
        ).thenReturn(
            Optional.of(product)
        );

        FundingServiceException exception = assertThrows(FundingServiceException.class, ()-> fundingService.productFunding(userId, productId, fundingRequest));

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SOLD_OUT);
    }

    @Test
    @DisplayName("정상 펀딩 테스트 확인")
    public void test2(){
        Long userId = 1L;
        Long productId = 1003L;
        FundingRequest fundingRequest = new FundingRequest(1000);

        Product product = Product.builder()
            .productId(productId)
            .title("환절기 토탈케어 펀딩")
            .targetFundingAmount(500000)
            .startDate(LocalDateTime.now().minusMonths(1))
            .finishDate(LocalDateTime.now().plusMonths(1))
            .fundingUserNumber(10)
            .fundingStatus("모집중")
            .totalFundingAmount(250000)
            .build();

        when(
            productRepository.findById(productId)
        ).thenReturn(
            Optional.of(product)
        );

        FundingResponse fundingResponse = fundingService.productFunding(userId, productId, fundingRequest);

        verify(productRepository, times(1)).save(product);

        //verify(fundingRepository, atMost(1)).save(funding);

        assertThat(fundingResponse.getFundingStatus()).isEqualTo("모집중");
    }





}
