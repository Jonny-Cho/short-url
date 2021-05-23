# short-url

* URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service

## 설치 & 빌드 방법


## 요구 사항 정리 (최종 제출 전 삭제할 것)

* [x] URL 입력폼 제공 및 결과 출력
* [ ] URL Shortening Key는 8 Character 이내
    * ver1. PK를 Base62로 인코딩
        * 문제점1. 요청수에 따라 순차적으로 증가하는 Shortening Key
        * 문제점2. 일정 PK 미만이라면 Shortening Key의 길이도 짧음 (5자리 이상은 되어야 하지 않을까?)
    * ver2. UUID를 Base62로 인코딩
* [x] 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답
* [x] 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
* [x] Shortening된 URL을 요청받으면 원래 URL로 리다이렉트
* [ ] Unit test 및 Integration test 작성
