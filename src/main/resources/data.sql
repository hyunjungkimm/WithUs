
--USER 데이터 생성
INSERT INTO USER (USER_ID, NAME) VALUES (1, 'jung');
INSERT INTO USER (USER_ID, NAME) VALUES (2, 'wook');
INSERT INTO USER (USER_ID, NAME) VALUES (3, 'kim');

--PRODUCT 데이터 생성
INSERT INTO PRODUCT (PRODUCT_ID, TITLE, TARGET_FUNDING_AMOUNT, START_DATE, FINISH_DATE, FUNDING_STATUS, FUNDING_USER_NUMBER, TOTAL_FUNDING_AMOUNT)
VALUES (1001, '환절기 토탈케어 펀딩', 500000, '2022-02-17 00:00:00', '2022-03-28 23:59:59', '모집중', 10, 250000);
INSERT INTO PRODUCT (PRODUCT_ID, TITLE, TARGET_FUNDING_AMOUNT, START_DATE, FINISH_DATE, FUNDING_STATUS, FUNDING_USER_NUMBER, TOTAL_FUNDING_AMOUNT)
VALUES (1002, '헤드셋 펀딩', 1000000, '2022-02-20 00:00:00', '2022-03-02 23:59:59', '모집완료', 55, 1000000);
INSERT INTO PRODUCT (PRODUCT_ID, TITLE, TARGET_FUNDING_AMOUNT, START_DATE, FINISH_DATE, FUNDING_STATUS, FUNDING_USER_NUMBER, TOTAL_FUNDING_AMOUNT)
VALUES (1003, '10분 완성 밀키트 펀딩', 500000, '2022-02-28 00:00:00', '2022-03-22 23:59:59', '모집중', 0, 0);
INSERT INTO PRODUCT (PRODUCT_ID, TITLE, TARGET_FUNDING_AMOUNT, START_DATE, FINISH_DATE, FUNDING_STATUS, FUNDING_USER_NUMBER, TOTAL_FUNDING_AMOUNT)
VALUES (1004, '손목보호대 펀딩', 300000, '2022-03-16 00:00:00', '2022-03-31 23:59:59', '모집중', 5, 15000);