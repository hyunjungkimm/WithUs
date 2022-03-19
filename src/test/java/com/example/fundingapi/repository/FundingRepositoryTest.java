package com.example.fundingapi.repository;

import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class FundingRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FundingRepository fundingRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("테스트01_정상_펀딩상품_전체_목록_조회")
    @Test
    public void 테스트01_정상_펀딩상품_전체_목록_조회(){
        assertThat(productRepository.findAll())
            .isNotNull()
            .hasSizeGreaterThanOrEqualTo(0)
            .hasOnlyElementsOfType(Product.class);
    }

    @DisplayName("테스트03_나의_펀딩상품_조회")
    @ParameterizedTest
    @ValueSource(longs={1,2,3})
    public void 테스트03_나의_펀딩상품_조회(Long userId){
        assertThat(fundingRepository.findByUserUserId(userId))
            .isNotNull()
            .hasSizeGreaterThanOrEqualTo(0)
            .hasOnlyElementsOfType(Funding.class);
    }

    @DisplayName("id 조회 데이터 검증 테스트")
    @Test
    public void test04() {
        User user = userRepository.findById(1L).get();

        assertThat(user.getName()).isEqualTo("jung");
    }
}
