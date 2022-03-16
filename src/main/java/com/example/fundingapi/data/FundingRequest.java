package com.example.fundingapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundingRequest {


    @NotNull(message = "펀딩금액은 필수이며 양수여야 합니다. 22")
    @Positive
    private int fundingAmount;


}








