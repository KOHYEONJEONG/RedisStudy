## 설치 및 설정
- MySql 8점대 버전 설치 + WorkBench 설치
- 레디스 설치

## 레디스 명령어
- 데이터(Key, value)저장
  - set [key 이름] [value]
    - 기본 : set hj:name hj
    - 띄어쓰기 포함("") : set hj:name "h j"

## 레디스 의존성 추가
- implementation 'org.springframework.boot:spring-boot-starter-data-redis'

## 포스트맨 실행
url : http://localhost:8080/boards