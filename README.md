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
| 회원가입 | POST   | `/user/signup` | {username, email, password  | 200: 정상가입 |
| 로그인 | POST    | `/user/login`   | {email, password} | 200: 정상로그인 |
| 유저 조회 | GET    | `/user/{id}` |   X          | 200: 정상조회 |



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

  
