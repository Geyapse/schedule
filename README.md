
# 📅 Schedule Management API

## 📌 프로젝트 소개

이 프로젝트는 Spring Boot를 사용하여 사용자 일정 관리 기능을 제공하는 웹 애플리케이션입니다.

# Schedule API 명세서

# 일정 명세서
| 기능    | Method | URL                   | request          | 상태코드    |
| ----- | ------ | --------------------- | -------------- | ------------ |
| 일정 생성 | POST   | `/schedule` | 요청 body  | 200: 정상등록 |
| 일정 목록 조회 | GET    | `/schedule`      | 요청 param | 200: 정상조회 |
| 일정 수정 | PUT    | `/schedule/{id}` | 요청 body  | 200: 정상수정 |
| 일정 삭제 | DELETE | `/schedule/{id}` | 요청 param     | 200: 정상삭제 |

# 유저 명세서
| 기능    | Method | URL                   | 요청값          | 상태코드    |
| ----- | ------ | --------------------- | -------------- | ------------ |
| 회원가입 | POST   | `/user` | {username, email, password}  | 200: 정상가입 |
| 로그인 | POST    | `/user/login`   | {email, password} | 200: 정상로그인 |
| 로그아웃 | POST    | `/user/logout`   | X | 200: 정상로그아웃 |
| 유저 전체 조회 | GET    | `/user` |   X          | 200: 정상조회 |
| 유저 삭제 | DELETE    | `/user/{id}` |   X          | 200: 정상삭제 |
| 유저 수정 | PUT    | `/user/{id}` |   X          | 200: 정상수정 |



# Schedule ERD


[Schedule]
* id (PK)
* user_id (FK)
* title
* content
* createdAt
* modifiedAt

[User]
* id (PK)
* username
* email
* password
* createdAt
* modifiedAt

| User Table           | Type            | Schedule Table     | Type            |
|----------------------|-----------------|--------------------|-----------------|
| id (PK)              | BIGINT          | id (PK)            | BIGINT          |
| username             | VARCHAR(50)     | title              | VARCHAR(100)    |
| email                | VARCHAR(100)    | content            | TEXT            |
| password             | VARCHAR(100)    | createdAt          | DATETIME        |
| createdAt            | DATETIME        | modifiedAt         | DATETIME        |
| modifiedAt           | DATETIME        | user_id (FK)       | BIGINT          |
  
