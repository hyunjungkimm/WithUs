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

    //insert
    @Test
    @DisplayName("펀딩 상품 등록")
    public void TestInsertProduct(){

        Product product = new Product();
        product.setProductId(1001);
        product.setTitle("환절기 토탈케어 펀딩");
        product.setTargetFundingAmount(500000);
        product.setStartDate("2022-02-17 00:00:00");
        product.setFinishDate("2022-02-28 23:59:59");
        product.setFundingStatus("모집중");

        productRepository.save(product);


        Product product2 = new Product();
        product2.setProductId(1002);
        product2.setTitle("헤드셋 펀딩");
        product2.setTargetFundingAmount(1000000);
        product2.setStartDate("2022-02-20 00:00:00");
        product2.setFinishDate("2022-03-02 23:59:59");
        product2.setFundingStatus("모집중");

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setProductId(1003);
        product3.setTitle("10분 완성 밀키트");
        product3.setTargetFundingAmount(500000);
        product3.setStartDate("2022-02-28 00:00:00");
        product3.setFinishDate("2022-03-10 23:59:59");
        product3.setFundingStatus("모집중");

        productRepository.save(product3);
    }

/*

    @Test
    @DisplayName("전체펀딩상품조회API")
    public void findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(){
        String dateTime = LocalDateTime.now()+"";
        String now = dateTime;
        List<Product> list = productRepository.findProductByFinishDateGreaterThanEqualAndStartDateLessThanEqual(dateTime,now);
        System.out.println(dateTime);
        for(Product product : list){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void findByProductIdTest(){
        Optional<Product> product =  productRepository.findById(1L);
        System.out.println(product);
    }
*/

}
