# WithUs - 이용자를 위한 펀딩서비스
![image](https://user-images.githubusercontent.com/97015607/155065889-64d8cf21-461e-485a-9cd7-979e5482247b.png)



## 프로젝트 요약

펀딩서비스를 UI 제외한 간소화한 REST API를 구현하는 것을 목표로 한다. 



### API 구현 
- 전체 펀딩 상품 조회 API 
- 펀딩하기 API
- 나의 펀딩상품 조회 API



### Project Stack 🛠



**Server**

- Java
- SpringBoot
- Oracle 
- JPA



## 솔루션  

![image](https://user-images.githubusercontent.com/97015607/155078421-5840da7a-617d-4617-9165-6fa9387f2522.png)

![image](https://user-images.githubusercontent.com/97015607/155078486-3288e6e8-7bf9-4298-8416-f519c17e8f35.png)

![image](https://user-images.githubusercontent.com/97015607/155078845-0121456e-c81a-42ca-aac8-f1f43dad2bea.png)


## schema(스키마)



### 도출된 요구사항 

WithUs의 펀딩 상품은 모집금액 달성시 펀딩이 마감되고 상품은 Sold out 처리 한다. 



#### 전체 펀딩 상품 조회 API 
- 펀딩기간(펀딩시작일시, 펀딩종료일시) 내의 상품만 응답한다. 
- 전체 펀딩상품 응답은 다음 내용을 포함한다. 
  - 펀딩상품 id, 펀딩명, 펀딩 목표 금액, 현재 펀딩 모집금액, 펀딩 사용자 수, 펀딩 상태(펀딩중, 펀딩완료), 펀딩 기간



#### 펀딩하기 API
- 펀딩사용자 식별값, 펀딩 상품 id, 펀딩 금액을 입력값으로 받는다. 
- 총 펀딩 모집금액을 넘어서면 sold-out 상태를 응답한다. 
- 펀딩 한 후에 
  - 상품의 현재 펀딩 모집금액, 펀딩 사용자 수를 update 한다. 
  - 나의 펀딩 상품을 update 한다. 



#### 나의 펀딩상품 조회 API 
- 내가 펀딩한 모든 펀딩상품을 반환한다. 
- 나의 펀딩상품 응답은 다음 내용을 포함한다. 
  - 펀딩상품 id, 펀딩명, 펀딩 목표 금액, 나의 펀딩 금액, 펀딩 일시 



### 개념적 설계 



#### Entity 
- 펀딩 상품 
- 펀딩 주문
- 회원은 입력만 받으므로 따로 엔티티로 만들지 않는다. 



#### 관계 추출



회원과 상품은 **N:M** 의 관계



회원은 여러 펀딩 상품을 펀딩할 수 있고, 하나의 펀딩상품을 여러 회원이 주문할 수 있다. 
회원과 펀딩 상품은 모두 주문이라는 하나의 개체에 선택적으로 참여하고 있다. 



#### DB 구현체 

![image](https://user-images.githubusercontent.com/97015607/155268565-ddd73dc9-5cd4-4033-ac85-b97e6cf45edf.png)



## Contributor

📧 E-mail: dosldnjsss@gmail.com

🐱 Github: https://github.com/hyunjungkimm
<br><br>
