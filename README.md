## Redis란?
- Redis는 데이터 처리 속도가 엄청 빠른 NoSQL 데이터베이스이다.
- 즉, 테이블과 스키마 구조를 사용하지 않는 비관계형 데이터베이스이다.

## NoSQL이란?
- Key-Value의 형태로 저장하는 데이터베이스라고 생각하면 된다.

## Redis 장점
- 레디스(Redis)는 인메모리(in-memory)에 모든 데이터를 저장한다.
- 그래서 데이터의 처리 성능이 굉장히 빠르다.

## RDBMS와 Redis 저장 공간 차이
- MySQL과 같은 RDMBMS의 데이터베이스는 대부분 디스크(Disk)에 데이터를 저장한다.
- Redis는 메모리(RAM)에 데이터를 저장한다
- 🚨디스크(Disk)보다 메모리(RAM)에서의 데이터 처리속도가 월등하게 빠르다.
- 이 때문에 Redis의 데이터 처리 속도가 RDBMS에 비해 훨씬 빠르다.

## Redis 주요 사용 사례
- ⭐캐싱
- 세션관리
- 실시간 분석 및 통계
- 메시지 큐
- 지리공간 인덱싱
- 속도 제한
- 실시간 채팅 및 메시징

## 내가 공부하는 이유
- 대부분 채용공고 보면 우대사항에 아래와 같은 내용이 있다.
- 대용량 트래픽 서비스 설계/개발/운영 경험 보유 또는 대용량 트래픽을 고려한 서버 설계
- MySql, Redis 개발 역량 보유
- Redis 개발 경험을 보유

---------------------------------------------
## 레디스 명령어(가장 많이 사용되는 7가지 명령어) 
- 1. 데이터(Key, value)저장
  - ✅set [key 이름] [value]
    - 예시 : set hj:name hj
    - 띄어쓰기 포함("") : set hj:name "h j"
  ->> OK 출력되면 저장된 것.
- 2. 조회
  - ✅get [key 이름]
    - 예시 : get hj:name
  - ✅모든 key 조회
    - 예시 : keys *
- 3. 삭제
   - ✅del [key 이름]
     - del hj:name
   ->> (integer) 1 '출력되면 삭제된 것'
   - ✅모두 삭제
     - flushall
     ->> OK
- 4. 데이터 저장 만료시간(TTL, Time To Live) 지정
  - 💡RDBMS(Disk)와는 다르게 레디스의 특성상 '메모리(RAM) 공간이 한정'되어 있기 때문에 모든 데이터를 레디스에 저장할 수 없다.
  - 따라서 만료시간(TTL)을 활용해 '자주 사용하는 데이터만 레디스에 저장'해놓고 쓰는 식으로 활용한다.
  - ✅설정 : set [key 이름] [value] ex 만료시간(초)
  - ✅만료시간 조회 : ttl [key 이름]
    ->> (integer) -2 '만료시간이 만료되어 없어진 key인 경우 -2 반환'
    ->> (integer) -1 '만료시간을 지정하지 않은 key인 경우 -1 반환'

  * 네이밍 컨벤션(콜론(:)을 많이 사용함)
  - 계층적으로 의미를 구분하기 위해서 콜론(:)을 사용하자
  - 예시) users:100:profile 
    - 사용자들(users) 중에서 PK가 100인 사용자(user)의 profile
  - 1. 가독성 : 데이터의 의미와 용도를 쉽게 파악할 수 있다.
  - 2. 일관성 : 컨벤션을 따름으로써 코드의 일관성이 높아지고 유지보수가 쉬워진다.
  - 3. 검색 및 필터링 용이성 : 패턴 매칭을 사용해 특정 유형의 Key를 쉽게 찾을 수 있다.
  - 4. 확장성 : 서로 다른 Key와 이름이 겹쳐 충돌할 일이 적어진다.
---------------------------------------------
## 설치 및 설정
- MySql 8점대 버전 설치 + WorkBench 설치
- 레디스 설치(윈도우)
  - https://ittrue.tistory.com/318#google_vignette
- 레디스 실행 확인
  - 명령어 : ping
    ->> PONG
  - 
## 레디스 의존성 추가
- implementation 'org.springframework.boot:spring-boot-starter-data-redis'
---------------------------------------------
## properties 파일 -> 변경 -> yml 파일로 통일
- src/main/resources/application.yml
---------------------------------------------
## 전략
- @Cacheable : Cache Aside 전략으로 캐싱이 적용됨. 
  - Cache Aside 전략은 캐시에서 데이터를 확인하고, 없다면 db를 통해 조회해오는 방식

## 포스트맨 실행(레디스 로컬 서버 먼저 실행 후 진행)
- url : http://localhost:8080/boards
