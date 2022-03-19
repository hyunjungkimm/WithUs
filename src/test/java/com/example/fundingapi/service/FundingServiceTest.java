package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.error.ErrorCode;
import com.example.fundingapi.exception.service.funding.FundingServiceException;
import com.example.fundingapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
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
    @DisplayName("펀딩하기 테스트")
    void fundingTest() {
        FundingRequest fundingRequest = new FundingRequest(1000);
        assertThatCode(
            () -> fundingService.productFunding(1, 1001,fundingRequest)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("펀딩하기 (멀티 스레드) 테스트")
    void fundingForMultiThreadTest() throws InterruptedException {
        Long userId = 1L;
        Long productId = 1003L;
        FundingRequest finalFundingRequest = new FundingRequest(200000);
        int numberOfExecute = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfExecute);

        boolean result;
        for(int i = 0; i < numberOfExecute; i++){
            int finalI = i;
            service.execute(() -> {
                try {
                    //테스트 될 메소드
                    fundingService.productFunding(userId, productId, finalFundingRequest);
                    System.out.println("i = " + finalI);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException());

    }


}
