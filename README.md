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

## 전략
@Cacheable : Cache Aside 전력으로 캐싱이 적용됨. 
ㄴCache Aside 전략은 캐시에서 데이터를 확인하고, 없다면 db를 통해 조회해오는 방식

## 포스트맨 실행(레디스 로컬 서버 먼저 실행 후 진행)
url : http://localhost:8080/boards

## 정상적으로 캐싱이 됐는지 확인하기
- Redis에 저장되어 있는 모든 key 조회 (명령어)
  - keys * 
- 특정 key의 Value 조회
  - get getBoards::boards:page:1:size:10
- 특정 key의 TTL 조회
  - ttl getBoards::boards:page:1:size:10 