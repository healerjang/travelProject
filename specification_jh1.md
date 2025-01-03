<!-- TOC -->
* [테이블](#테이블)
  * [1. 사용자(User) 테이블](#1-사용자user-테이블)
  * [2. 여행 상품(Product) 테이블](#2-여행-상품product-테이블)
  * [3. 예약(Reservation) 테이블](#3-예약reservation-테이블)
  * [4. 후기(Review) 테이블](#4-후기review-테이블)
  * [5. 장바구니 테이블](#5-장바구니-테이블)
  * [6. 자유게시판](#6-자유게시판)
  * [7. 여행지(나라,도시)](#7-여행지나라도시)
  * [8. 댓글](#8-댓글)
* [구현해야 할 것](#구현해야-할-것)
* [개발도구](#개발도구)
<!-- TOC -->

# 테이블

## 1. 사용자(User) 테이블

사용자 정보를 저장.

- user_id (PK, INT, AUTO_INCREMENT): 사용자 ID
- username (VARCHAR): 사용자 이름
- email (VARCHAR): 이메일
- password (VARCHAR): 비밀번호 (암호화 필요)
- phone (VARCHAR): 전화번호
- role (ENUM): 권한 (e.g., USER, ADMIN)
- created_at (DATETIME): 계정 생성일
- updated_at (DATETIME): 계정 정보 수정일
- userpoint : 포인트(결제용)

## 2. 여행 상품(Product) 테이블

여행 상품 정보를 저장.

- product_id (PK, INT, AUTO_INCREMENT): 상품 ID
- name (VARCHAR): 상품명
- description (TEXT): 상품 설명
- price (DECIMAL): 가격
- location : 여행지 (fk, db 따로 빼야하니까)
- start_date (DATE): 여행 시작일
- end_date (DATE): 여행 종료일
- capacity (INT): 최대 인원수
- created_at (DATETIME): 상품 등록일
- updated_at (DATETIME): 상품 수정일
- imgname : 파일명
- imgpath : 파일경로

## 3. 예약(Reservation) 테이블

사용자와 여행 상품 간의 예약 정보를 저장.

- reservation_id (PK, INT, AUTO_INCREMENT): 예약 ID
- user_id (FK, INT): 사용자 ID
- product_id (FK, INT): 여행 상품 ID
- reservation_date (DATETIME): 예약일
- status (ENUM): 예약 상태 (e.g., PENDING, CONFIRMED, CANCELLED)
- total_price (DECIMAL): 총 결제 금액
- created_at (DATETIME): 예약 생성일
- updated_at (DATETIME): 예약 수정일

## 4. 후기(Review) 테이블

사용자가 남긴 리뷰를 저장.

- review_id (PK, INT, AUTO_INCREMENT): 리뷰 ID
- user_id (FK, INT): 사용자 ID
- product_id (FK, INT): 여행 상품 ID
- rating (INT): 평점 (1-5)
- comment (TEXT): 리뷰 내용
- created_at (DATETIME): 리뷰 작성일
- updated_at (DATETIME): 리뷰 수정일

## 5. 장바구니 테이블

- pno(product num ++i ) (pk)
- product_id
- user_id

## 6. 자유게시판

## 7. 여행지(나라,도시)

- lno(location number)(pk)
- country
- city


## 8. 댓글





# 구현해야 할 것

1. 로그인 폼 + 자동로그인 (쿠키)
2. 회원가입 폼
3. 메인 홈페이지 - nav 로그인 , 회원가입, 예약확인 장바구니 | 검색 - 북미, 아시아 , 유럽, 오세아니아, 국내여행
    1. 월간 베스트 1~10위
4. 메인페이지 로드시 팝업창(쿠키 등록해서 오늘하루 그만보기(valid 24hours)
5. 여행상품 상세페이지
6. 내 장바구니(찜)
7. 내정보(예약확인)
8. 예약취소 페이지(delete -> db에서 삭제)
9. 관리자(권한설정)-> 모든 회원 정보 조회,  게시글 관리 (컨트롤러로) -> 여행상품 등록
10. 관리자 게시판

====

# 개발도구

- spring boot
- maria db
- bootstrap
- java
