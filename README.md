# short-url

* URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
* 테스트 서버 주소 : http://15.165.88.81:8080/

## 설치 & 빌드 방법

### 1. clone

```shell
git clone https://github.com/Jonny-Cho/short-url.git
```

### 2. db 설정

/project/real-application.yml 파일 생성 후 DB 정보 입력

```yml
spring:
  datasource:
    url: jdbc:mysql://host:port/db
    username: username
    password: password
    driver-class-name: org.mariadb.jdbc.Driver
```

### 3. 실행

```shell
./gradlew bootRun
```

## 요구 사항 정리

* [x] URL 입력폼 제공 및 결과 출력
* [x] URL Shortening Key는 8 Character 이내
    * RandomString 클래스를 활용
        * 5 ~ 8자리의 랜덤한 문자를 사용
        * 중복의 가능성이 남아 있기 때문에 DB에 이미 존재하는 Shortening Key인지 체크해야함
* [x] 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답
* [x] 동일한 URL에 대한 요청 수 정보를 가져야 함
* [x] Shortening된 URL을 요청받으면 원래 URL로 리다이렉트
* [x] test code 작성
