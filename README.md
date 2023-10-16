# README

# [ 뉴스의 재발견, 뉴레카 ]

## 📰 프로젝트 소개

**Newreka: 뉴스관련 실시간 핫토픽 키워드를 제공하는 온라인 플랫폼**

Newreka는 평소 뉴스 읽기를 버거워하던 사람들을 위해 제공되는 서비스입니다. 사람들에게 뉴스 진입 장벽을 낮추고, 시간과 공간의 제약 없이 더 많은 기회를 제공합니다. 또한, 뉴스를 읽었음을 문제를 통해 검증하고, 얼마나 많은 뉴스를 읽었는지에 대한 객관적 지표를 제공함니다.

## ⚙️ 기술 스택

### BackEnd

java, python, springboot, jpa, spring security

logstash, filebeat, kafka, elasticsearch, kibana, mysql

### FrontEnd

react

### Infra

jenkins, aws, ec2, docker, nginx

### Cooperation

gitlab, jira, notion, mattermost

## 💡 주요 기능

---

### 시간대별 Keyword Top 10

![02 핫토픽.gif](C:\Users\SSAFY\Desktop\새%20폴더%20(4)\S09P22D103\resource\02_%ED%95%AB%ED%86%A0%ED%94%BD.gif)

- 매시 정각부터 시작하여 10분마다 실행되는 작업
- 로직
  - 네이버 뉴스 크롤링 데이터를 3시간 단위로 처리하며, 10분 간격으로 sliding
  - 제목 데이터를 가공 후 Komoran을 이용해 형태소 분석
  - 시간에 따라 서로 다른 가중치를 두어 빈도수 측정 후 상위 10개 키워드 선정

### Keyword 관련뉴스 3줄요약 & 관련뉴스 기사

![03 토픽 관련 요약 및 기사.gif](README%205c9a23c11bb248c79158dd2cccc96db3/03_%25ED%2586%25A0%25ED%2594%25BD_%25EA%25B4%2580%25EB%25A0%25A8_%25EC%259A%2594%25EC%2595%25BD_%25EB%25B0%258F_%25EA%25B8%25B0%25EC%2582%25AC.gif)

- 매시 1분부터 시작하여 10분마다 실행되는 작업
- 관련 뉴스 선정은 Elasticsearch에 검색한 결과를 이용
  - Carrot2 플러그인의 Lingo 알고리즘에 의해 선택되는 유사도 높은 기사 5개를 선정
  - Nori 형태 소 분석기를 커스텀하여 적용해 한글 검색 문제 해결
- Naver OpenAPI를 이용해 자동화

### Keyword 관련뉴스 퀴즈

![04 토픽 관련 문제 풀기.gif](README%205c9a23c11bb248c79158dd2cccc96db3/04_%25ED%2586%25A0%25ED%2594%25BD_%25EA%25B4%2580%25EB%25A0%25A8_%25EB%25AC%25B8%25EC%25A0%259C_%25ED%2592%2580%25EA%25B8%25B0.gif)

- 매시 2분부터 시작하여 10분마다 실행되는 작업
- OpenAI API를 이용해 자동화

### Keyword 뉴스 스크랩 및 리다이랙트

![06 뉴스 스크랩 및 리다이렉트.gif](README%205c9a23c11bb248c79158dd2cccc96db3/06_%25EB%2589%25B4%25EC%258A%25A4_%25EC%258A%25A4%25ED%2581%25AC%25EB%259E%25A9_%25EB%25B0%258F_%25EB%25A6%25AC%25EB%258B%25A4%25EC%259D%25B4%25EB%25A0%2589%25ED%258A%25B8.gif)

### 퀴즈관련 Keyword 수집

![05 키워드 수집 기능.gif](README%205c9a23c11bb248c79158dd2cccc96db3/05_%25ED%2582%25A4%25EC%259B%258C%25EB%2593%259C_%25EC%2588%2598%25EC%25A7%2591_%25EA%25B8%25B0%25EB%258A%25A5.gif)

### 구독자 이메일 발송 서비스

![07 이메일 서비스.gif](README%205c9a23c11bb248c79158dd2cccc96db3/07_%25EC%259D%25B4%25EB%25A9%2594%25EC%259D%25BC_%25EC%2584%259C%25EB%25B9%2584%25EC%258A%25A4.gif)

### 데이터 시각화

![Untitled](README%205c9a23c11bb248c79158dd2cccc96db3/Untitled.png)

- Kibana를 통해 실시간 데이터 시각화

## 📄 아키텍처 설계도

![Untitled](README%205c9a23c11bb248c79158dd2cccc96db3/Untitled%201.png)

### Data Pipeline

![Untitled](README%205c9a23c11bb248c79158dd2cccc96db3/Untitled%202.png)

### Kafka Cluster

![architecture.png](README%205c9a23c11bb248c79158dd2cccc96db3/architecture.png)

### Elasticsearch Cluster

![Untitled](README%205c9a23c11bb248c79158dd2cccc96db3/Untitled%203.png)

## 📊 ERD

![Untitled](README%205c9a23c11bb248c79158dd2cccc96db3/Untitled%204.png)

## ✌️ 팀원 소개

| 이름  | 역할              | 주요 임무 (이건 깃허브에 올리지 말까 생각중. 나중에 자소서 대비 적어보면 좋을 듯)           |
| --- | --------------- | ---------------------------------------------------------- |
| 김정환 | BE, DevOps      | 서버 구축, CI/CD 자동 배포, Nginx, 스크랩 기능                          |
| 윤우혁 | BE, Data, Infra | 아키텍쳐 설계, Elasticsearch, Logstash, Kibana, 핫토픽 선정, 연관 뉴스 선정 |
| 천병찬 | BE, Data        | ERD 설계, JPA, JWT, 계정 관리, 이메일 서비스, File Beat                |
| 채경호 | BE, Data        | JPA, 뉴스 크롤링, 요약 기능, 퀴즈 생성, 스크랩 기능                          |
| 박종욱 | FE, BE, Data    | UI/UX, 프론트엔드, 뉴스 크롤링, 핫토픽 선정, Kafka                        |
| 김선영 | FE, BE, Data    | UI/UX, 프론트엔드, Logstash                                     |