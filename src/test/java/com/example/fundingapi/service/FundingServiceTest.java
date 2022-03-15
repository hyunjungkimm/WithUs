package com.example.fundingapi.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class FundingServiceTest {

    @Autowired
    private FundingService fundingService;

    @Test
    @DisplayName("펀딩하기 (멀티 스레드) 테스트 - 오류")
    public void fundingForMultiThreadTest() throws InterruptedException {
        ExecutorService executorService = null;
        AtomicInteger successCount = new AtomicInteger();
        int numberOfExcute = 10 ;
        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(numberOfExcute);

        for(int i = 0; i < numberOfExcute; i++){
            service.execute(() -> {
                try{
                    fundingService.lockTest(1001);
                    successCount.getAndIncrement();
                    System.out.println("성공");
                } catch (ObjectOptimisticLockingFailureException oe){
                    System.out.println("충돌감지");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                latch.countDown();
            });
        }
        latch.await();

        assertThat(successCount.get()).isEqualTo(5);
    }


}
