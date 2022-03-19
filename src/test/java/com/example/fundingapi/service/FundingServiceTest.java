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
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FundingServiceTest {

    @InjectMocks
    private FundingServiceImpl fundingService;

    @Mock
    private ProductRepository productRepository;


    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);//이 클래스  - this
    }

    @Test
    @DisplayName("sold out 테스트")
    public void test1() {

        when(
            productRepository.findById(1002L)
        ).thenReturn(
            Optional.of(new Product())
        );

        Long userId = 1L;
        Long productId = 1002L;
        FundingRequest fundingRequest = new FundingRequest(1000);

        FundingServiceException exception = assertThrows(FundingServiceException.class, ()-> fundingService.productFunding(userId, productId, fundingRequest));

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.SOLD_OUT);
    }


    @Test
    @DisplayName("회원이 아닌 경우 테스트")
    public void test2(){

        when(
            productRepository.findById(1002L)
        ).thenReturn(
            Optional.of(new Product())
        );

        Long userId = 5L;
        Long productId = 1002L;


        FundingRequest fundingRequest = new FundingRequest(1000);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> fundingService.productFunding(userId, productId, fundingRequest));

        assertThat(exception.getMessage()).isEqualTo(ErrorCode.NOT_SIGNED_UP_USER);


    }


}
