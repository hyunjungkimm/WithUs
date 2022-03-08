package com.example.fundingapi.data;

import com.example.fundingapi.domain.Funding;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Data {

    long productId = 1;
    String finishDate = "20220331";
    String fundingStatus = "NORMAL";
    String title = "펀딩 제목";
    private int targetFundingAmount=10000;
    private String startDate = "20220301";
    private int totalFundingAmount=1000;
    private int fundingUserNumber = 1 ;

}


/*
:insert into users(user_id, name) values(1,'jung');

insert into product(product_id, finish_date, funding_status, funding_user_number, start_date, target_funding_amount, title, total_funding_amount)
values('1','20220331','NORMAL','1','20220301',10000,'펀딩 제목',1000);
*/