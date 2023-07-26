# MiniProject_Basic_KimMinWoo

## 🦁멋사마켓 - 미니프로젝트🦁

## 🛒멋사마켓
```text
🌩️번개장터, 중고나라 등 착안하여 만든 중고거래 플랫폼 입니다.
```

## 🧾미니프로젝트 소개
```text
사용자가 중고물품을 자유롭게 등록하고, 댓글을 통하여 구매제안 및 최종적으로 수락할 수 있는
형태의 중고거래 플랫폼을 구현한 프로젝트입니다.
```

### 1차 미니프로젝트 기간
```text
💻 6.29.23 - 7.4.23
```

<details>
<summary>1차 미니프로젝트</summary>
<div markdown="1">

## ⚙️개발 환경
```text
1. Java17
2. JDK 17
3. IDE : Intellj IDEA
4. Project : Gradle[Groovy]
5. SpringBoot : 3.1.1
6. Dependency : Spring Web, Srping Boot DevTools, Lombok, Thymeleaf, Validation, Spring Data JPA, SQLite
7. DB : SQLite
```

## 📝멋사마켄 ERD
<img width="660" alt="erd" src="https://github.com/CodingScript990/board-springbootBE/assets/70142090/dbcdfe91-2ba5-424f-b1ae-1c35accbc730">

## ⭐요구사항

### 1️⃣ 중고 물품 관리(6.29)

```text
1. 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 제목, 설명, 최소 가격, 작성자이다.
    2. 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 최초로 물품이 등록될 때, 중고 물품의 상태는 판매중 상태가 된다.
2. 등록된 물품 정보는 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
    2. 전체 조회, 단일 조회 모두 가능하다.
3. 등록된 물품 정보는 수정이 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 물품 정보에 이미지를 첨부할 수 있다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    2. 이미지를 관리하는 방법은 자율이다.
5. 등록된 물품 정보는 삭제가 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
```
### 2️⃣ 중고 물품 댓글(7.3)

```text
1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 대상물품, 댓글 내용, 작성자이다.
    2. 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
2. 등록된 댓글은 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
3. 등록된 댓글은 수정이 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 댓글은 삭제가 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
5. 댓글에는 초기에 비워져 있는 **답글** 항목이 존재한다. 
    1. 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
    2. 답글은 댓글에 포함된 공개 정보이다.
```
### 3️⃣ 구매 제안(7.4)
```text
1. 등록된 물품에 대하여 구매 제안을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 대상물품, 제안 가격, 작성자이다.
    2. 또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 구매 제안이 등록될 때, 제안의 상태는 제안 상태가 된다.
2. 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
    1. 대상 물품의 주인은, 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다. 이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다.
    2. 등록한 사용자는, 조회를 위해서 자신이 사용한 작성자와 비밀번호를 첨부해야 한다. 이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다.
3. 등록된 제안은 수정이 가능하다. 
    1. 이때, 제안이 등록될때 추가한 작성자와 비밀번호를 첨부해야 한다.
4. 등록된 제안은 삭제가 가능하다. 
    1. 이때, 제안이 등록될때 추가한 작성자와 비밀번호를 첨부해야 한다.
5. 대상 물품의 주인은 구매 제안을 수락할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 수락이 된다.
6. 대상 물품의 주인은 구매 제안을 거절할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 거절이 된다.
7. 구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다. 
    1. 이를 위해서 제안을 등록할 때 사용한 작성자와 비밀번호를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 확정 상태가 된다.
    3. 구매 제안이 확정될 경우, 대상 물품의 상태는 판매완료가 된다.
    4. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 거절이 된다.
```

## 👨‍💻Development Period

### 📆 2023.6.29[목]
1. Project 생성
2. 중고 물품 관리(요구사항)에 맞게 ItemController, ItemService, ItemRepository, ItemEntity, ItemDto를 생성
3. <b>필수기능 구현</b>
    * 물품 등록 기능 구현 - Create[물품을 등록하게 만들고 반드시 사용자의 입력 유효성 검증하도록 조치]
    * 전체조회 페이징 - ReadAll[모든 물품을 조회가 되도록 조치], 단일 조회 기능 구현 - ReadOne[해당 물품만 조회가 되도록 조치]
    * 물품 수정 기능 구현 - Update[물품등록 정보만 수정되게 만듬]
    * 물품정보 이미지 첨부 구현 - Update[물품 이미지파일을 첨부할 수 있게 만듬]
    * 물품 삭제 기능 구현 - Delete[물품 등록한 사용자의 아이디, 패스워드 유효성 검증 후 물품 삭제 되도록 조치]
4. PostMan API를 이용하여 HTTP Request, Response Test함 - CRUD

### 📆 2023.7.2[일]
1. 중고 물품 댓글(요구사항)에 맞게 CommentController, CommentService, CommentRepository, CommentEntity, CommentDto를 생성
2. <b>필수기능 구현</b>
    * 게시글 댓글등록 기능 구현 - Create[물품이 등록되어 있는지 확인 후 댓글이 등록 되도록 조치]
    * 게시글 댓글수정 기능 구현 - Update[댓글을 등록한 사용자, 답글을 달아주는 작성자까지 수정할 수 있게 조치]
    * 게시글 댓글삭제 기능 구현 - Delete[사용자의 유효성 검증 후 댓글 삭제 조치]
      
### 📆 2023.7.3[월]
1. 중고 물품 댓글 나머지 요구사항 실시
2. <b>필수기능 구현</b>
    * 게시글 댓글조회 기능 구현 - Read[모든 댓글들이 보여지도록 조치]
  
### 📆 2023.7.4[화]
1. 구매 제안(요구사항)에 맞게 NegotiationController, NegotiationService, NegotiationRepository, NegotiationEntity, NegotiationDto를 생성
2. <b>필수기능 구현</b>
    * 구매제안 등록 기능 구현 - Create[사용자의 입력 유효성 검증 후 구매제안 등록을 할 수 있게 조치]
    * 구매제안 조회 기능 구현 - Read[구매제안 작성자, 패스워드를 비교 후 일치하면 내역이 보여지도록 조치]
    * 구매제안 수정 기능 구현(가격) - Update[수정 -> itemId 체크, 작성자, 패스워드 유효성 체크 후 가격만 수정이 되도록 조치]
    * 구매제안 수정 기능 구현(제안상태) - Update[제안상태 -> 제안 상태에서 수락 또는 거절을 등록한 경우 상태값이 변경되도록 조치]
    * 구매제안 수정 기능 구현(제안상태) - Update[제안상태 -> 구매제안을 수락했는지를 유효성 검증을 하고, 확정 상태로 등록한 경우 물품 상태도 판매중에서 판매완료로 변경이 되도록 조치]
    * 구매제안 삭제 기능 구현 - Delete[제안 등록한 작성자, 패스워드 유효성 체크 후 등록된 제안이 삭제 되도록 조치]

## 🦁멋사마켓 결과물🦁

### 1️⃣중고물품 관리

* 물품등록 - POST /items

[Request]
```json
{
    "title": "아이폰14 팝니다",
    "description": "아이폰14 1TB 팔아요",
    "minPriceWanted": 1600000,
    "writer": "jeeho.dev",
    "password": "1qaz2wsx"
}
```
[Response]
```json
{
    "message": "등록이 완료되었습니다."
}
```
* 물품조회(전체) - GET /items?page={page}&limit={limit}

   1. Query Params
      - page = 0, limit = 25

[Request] => X 

[Response]
```json
{
    "content": [
        {
            "id": 2,
            "title": "아이폰14 플러스 팝니다",
            "description": "아이폰14 플러스 1TB 팔아요",
            "minPriceWanted": 1750000,
            "status": "판매중",
            "writer": "jeeho.dev",
            "password": "1qaz2wsx"
        },
        {
            "id": 1,
            "title": "아이폰14 팝니다",
            "description": "아이폰14 1TB 팔아요",
            "minPriceWanted": 1600000,
            "status": "판매중",
            "writer": "jeeho.dev",
            "password": "1qaz2wsx"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 25,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```
* 물품조회(단일) - GET /items/{itemId}

[Request] => X

[Response]
```json
{
    "id": 1,
    "title": "아이폰14 팝니다",
    "description": "아이폰14 1TB 팔아요",
    "minPriceWanted": 1600000,
    "status": "판매중",
    "writer": "jeeho.dev",
    "password": "1qaz2wsx"
}
```
* 물품정보 수정 - PUT /items/{itemId}

[Request]
```json
{
    "title": "아이폰 14 프로 중고 팝니다",
    "description": "아이폰 14 프로 중고 1TB 입니다",
    "minPriceWanted": 2000000,
    "status": "판매중",
    "writer": "jeeho.dev",
    "password": "1qaz2wsx"
}
```
[Response]
```json
{
    "message": "물품이 수정되었습니다."
}
```
* 물품이미지 수정 - PUT /items/{itemId}/image

[Request]
```text
image:    iphone14Pro.png [File]
writer:   jeeho.dev
password: 1qaz2wsx
```
[Response]
```json
{
    "message": "이미지 등록이 완료되었습니다."
}
```
* 물품삭제 - DELETE /items/{itemId}
[Request]
```json
{
    "writer": "jeeho.dev",
    "password": "1qaz2wsx"
}
```
[Response]
```json
{
    "message": "물품을 삭제했습니다."
}
```

### 2️⃣중고물품 댓글

* 댓글등록 - POST /items/{itemId}/comments

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234",
    "content": "얼마나 더 할인이 가능할까요?"
}
```
[Response]
```json
{
    "message": "댓글이 등록되었습니다."
}
```

* 댓글조회 - GET /items/{itemId}/comments

[Request] => X 

[Response]
```json
{
    "content": [
        {
            "id": 1,
            "itemId": 1,
            "writer": "jeeho.edu",
            "password": "qwerty1234",
            "content": "얼마나 더 할인이 가능할까요?"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageSize": 25,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

* 댓글수정(댓글작성자) - PUT /items/{itemId}/comments/{commentId}

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234",
    "content": "거래 가능하다면, 어디에서 거래를 하면 될까요?"
}
```
[Response]
```json
{
    "message": "댓글이 수정되었습니다."
}
```

* 댓글수정(댓글답변자) - PUT /items/{itemId}/comments/{commentId}/reply

[Request]
```json
{
    "writer": "jeeho.dev",
    "password": "1qaz2wsx",
    "reply": "거래 가능합니다! 잠실역 3번출구 앞에서 하시죠?"
}
```
[Response]
```json
{
    "message": "댓글에 답변이 추가되었습니다."
}
```

* 댓글삭제 - DELETE /items/{itemId}/comments/{commentId}

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234"
}
```
[Response]
```json
{
    "message": "댓글을 삭제했습니다."
}
```

### 3️⃣구매제안

* 구매제안 등록 - POST /items/{itemId}/proposals

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234",
    "suggestedPrice": 1300000
}
```
[Response]
```json
{
    "message": "구매 제안이 등록되었습니다."
}
```

* 구매제안 조회 - GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=0
    
     1. Query Params
          - writer, password, page = 0

[Request] => X 

[Response]
```json
{
    "content": [
        {
            "id": 1,
            "itemId": 1,
            "suggestedPrice": 1300000,
            "status": "제안",
            "writer": "jeeho.edu",
            "password": "qwerty1234"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageSize": 25,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

* 구매제안 수정(가격) - PUT /items/{itemId}/proposals/{proposalId}

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234",
    "suggestedPrice": 1300000
}
```
[Response]
```json
{
    "message": "제안이 수정되었습니다."
}
```

* 구매제안 수정(제안 - 수락,거절) - PUT /items/{itemId}/proposals/{proposalId}

[Request]
```json
{
    "writer": "jeeho.dev",
    "password": "1qaz2wsx",
    "status": "수락"
}
{
    "writer": "jeeho.dev",
    "password": "1qaz2wsx",
    "status": "거절"
}
```
[Response]
```json
{
    "message": "제안의 상태가 변경되었습니다."
}
```

* 구매제안 수정(제안 - 확정) - PUT /items/{itemId}/proposals/{proposalId}

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234",
    "status": "확정"
}
```
[Response]
```json
{
    "message": "구매가 확정되었습니다."
}
```

* 구매제안 삭제 - DELETE /items/{itemId}/proposals/{proposalId}

[Request]
```json
{
    "writer": "jeeho.edu",
    "password": "qwerty1234"
}
```
[Response]
```json
{
    "message": "제안을 삭제했습니다."
}
```


</div>
</details>

### 2차 미니프로젝트 기간
```text
💻 7.26.23 - 8.2.23
```

<details>
<summary>2차 미니프로젝트 기간</summary>
<div markdown="1">

## ⚙️개발 환경
```text
1. Java17
2. JDK 17
3. IDE : Intellj IDEA
4. Project : Gradle[Groovy]
5. SpringBoot : 3.1.1
6. Dependency : Spring Web, Srping Boot DevTools, Lombok, Thymeleaf, Validation, Spring Data JPA, SQLite
7. DB : SQLite
```

</div>
</details>
