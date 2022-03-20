package com.example.fundingapi.controller;

import com.example.fundingapi.data.FundingRequest;
import com.example.fundingapi.data.FundingResponse;
import com.example.fundingapi.domain.Funding;
import com.example.fundingapi.domain.Product;
import com.example.fundingapi.domain.User;
import com.example.fundingapi.dto.MyFundingDTO;
import com.example.fundingapi.repository.UserRepository;
import com.example.fundingapi.service.FundingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FundingController.class)
class FundingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FundingService fundingService;
    @MockBean
    private UserRepository userRepository;



    @DisplayName("전체 펀딩 상품 조회 api 정상 테스트")
    @Test
    public void test1() throws Exception {

        when(
            fundingService.productList()
        ).thenReturn(
            new ArrayList<Product>()
        );

        mockMvc.perform(
            get("/productList")
        ).andExpect(status().isOk());
    }

    @DisplayName("펀딩하기 api 정상 테스트")
    @Test
    public void test2() throws Exception {

        Long userId = 1L;
        Long productId = 1001L;
        FundingRequest fundingRequest = new FundingRequest(1000);

        when(
            fundingService.productFunding(userId, productId, fundingRequest)
        ).thenReturn(
            new FundingResponse("모집중")
        );

        when(
            userRepository.findById(userId)
        ).thenReturn(
            this.makeOptionalUser()
        );

        String content = objectMapper.writeValueAsString(fundingRequest);

        mockMvc.perform(
            post("/products/"+productId.toString()+"/funding")
                .header("X-USER-ID",userId)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    private Optional<User> makeOptionalUser() {
        User user = User.builder()
                        .userId(1L)
                        .name("jung")
                        .build();

        return Optional.of(user);
    }

    @DisplayName("내 펀딩 상품 조회하기 api 정상 테스트")
    @Test
    public void test3() throws Exception{

        Long userId = 1L;

        when(
            fundingService.fundingList(userId)
        ).thenReturn(
          new ArrayList<MyFundingDTO>()
        );

        when(
            userRepository.findById(userId)
        ).thenReturn(
            this.makeOptionalUser()
        );


        mockMvc.perform(
            get("/fundingList")
                .header("X-USER-ID", userId)
        ).andExpect(status().isOk());
    }
}