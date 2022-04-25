package com.example.fundingapi;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.repository.ProductRepository;
import com.example.fundingapi.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("전체펀딩상품조회API")
    public void findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(){
        LocalDateTime dateTime = LocalDateTime.now();
        Pageable pageable = PageRequest.of(0, 5);
        Page<Product> list = productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dateTime,dateTime,pageable);
        System.out.println(dateTime);
        for(Product product : list){
            System.out.println(product.getProductId());
        }
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByProductIdTest(){
        Optional<Product> product =  productRepository.findById(1L);
        System.out.println(product);
    }


}
