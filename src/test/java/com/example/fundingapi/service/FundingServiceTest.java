package com.example.fundingapi.service;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityNotFoundException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@SpringBootTest
public class FundingServiceTest {

    @Autowired
    private FundingService fundingService;

    @Autowired
    private ProductRepository productRepository;
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
