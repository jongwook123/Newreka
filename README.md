# [ 뉴스의 재발견, 뉴레카 ]

## 📰 프로젝트 소개

**Newreka: 뉴스관련 실시간 핫토픽 키워드를 제공하는 온라인 플랫폼**

Newreka는 평소 뉴스 읽기를 버거워하던 사람들을 위해 제공되는 서비스입니다. 사람들에게 뉴스 진입 장벽을 낮추고, 시간과 공간의 제약 없이 더 많은 기회를 제공합니다. 또한, 뉴스를 읽었음을 문제를 통해 검증하고, 얼마나 많은 뉴스를 읽었는지에 대한 객관적 지표를 제공함니다.

## ⚙️ 기술 스택

### BackEnd

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=black"> <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=black">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=black">
<img src="https://img.shields.io/badge/JPA-61DAFB?style=for-the-badge&logo=JPA&logoColor=black">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=black">

<img src="https://img.shields.io/badge/logstash-005571?style=for-the-badge&logo=logstash&logoColor=white"> <img src="https://img.shields.io/badge/filebeat-yellow?style=for-the-badge&logo=filebeat&logoColor=white">
<img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white">
<img src="https://img.shields.io/badge/elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white">
<img src="https://img.shields.io/badge/kibana-005571?style=for-the-badge&logo=kibana&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=black">

### FrontEnd

<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">


### Infra

<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=black"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=black">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=black">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=black">


### Cooperation

<img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=black"> <img src="https://img.shields.io/badge/jirasoftware-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=black">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=black">

## 💡 주요 기능

---

### 시간대별 Keyword Top 10

![02_%ED%95%AB%ED%86%A0%ED%94%BD](https://github.com/jongwook123/Newreka/assets/122584199/739d38fa-4705-497a-bd4d-aebeb321b7e4)

- 매시 정각부터 시작하여 10분마다 실행되는 작업
- 로직
  - 네이버 뉴스 크롤링 데이터를 3시간 단위로 처리하며, 10분 간격으로 sliding
  - 제목 데이터를 가공 후 Komoran을 이용해 형태소 분석
  - 시간에 따라 서로 다른 가중치를 두어 빈도수 측정 후 상위 10개 키워드 선정

### Keyword 관련뉴스 3줄요약 & 관련뉴스 기사

![03_%ED%86%A0%ED%94%BD_%EA%B4%80%EB%A0%A8_%EC%9A%94%EC%95%BD_%EB%B0%8F_%EA%B8%B0%EC%82%AC](https://github.com/jongwook123/Newreka/assets/122584199/dfd0db7d-e279-4439-9069-5f521da3e970)

- 매시 1분부터 시작하여 10분마다 실행되는 작업
- 관련 뉴스 선정은 Elasticsearch에 검색한 결과를 이용
  - Carrot2 플러그인의 Lingo 알고리즘에 의해 선택되는 유사도 높은 기사 5개를 선정
  - Nori 형태 소 분석기를 커스텀하여 적용해 한글 검색 문제 해결
- Naver OpenAPI를 이용해 자동화

### Keyword 관련뉴스 퀴즈

![04_%ED%86%A0%ED%94%BD_%EA%B4%80%EB%A0%A8_%EB%AC%B8%EC%A0%9C_%ED%92%80%EA%B8%B0](https://github.com/jongwook123/Newreka/assets/122584199/5d2ca091-cd72-4cc8-adf9-ae2a3d7fcbf0)

- 매시 2분부터 시작하여 10분마다 실행되는 작업
- OpenAI API를 이용해 자동화

### Keyword 뉴스 스크랩 및 리다이랙트

![06_%EB%89%B4%EC%8A%A4_%EC%8A%A4%ED%81%AC%EB%9E%A9_%EB%B0%8F_%EB%A6%AC%EB%8B%A4%EC%9D%B4%EB%A0%89%ED%8A%B8](https://github.com/jongwook123/Newreka/assets/122584199/5c027d21-e5bf-4116-8ac4-e7d94415c686)

### 퀴즈관련 Keyword 수집

![05_%ED%82%A4%EC%9B%8C%EB%93%9C_%EC%88%98%EC%A7%91_%EA%B8%B0%EB%8A%A5](https://github.com/jongwook123/Newreka/assets/122584199/20281cc5-a4d2-42ad-91a1-7dfbe990735b)


### 구독자 이메일 발송 서비스

![07_%EC%9D%B4%EB%A9%94%EC%9D%BC_%EC%84%9C%EB%B9%84%EC%8A%A4](https://github.com/jongwook123/Newreka/assets/122584199/4397e22d-32ab-4aac-9c23-96ed8a59d2c6)


### 데이터 시각화

![Untitled](https://github.com/jongwook123/Newreka/assets/122584199/64cb1130-d81b-4acd-b89e-b698c070860e)

- Kibana를 통해 실시간 데이터 시각화

## 📄 아키텍처 설계도

![Untitled 1](https://github.com/jongwook123/Newreka/assets/122584199/5e15d027-be24-45c1-a301-241bbe83a972)

### Data Pipeline

![Untitled 2](https://github.com/jongwook123/Newreka/assets/122584199/162dc0c4-0535-47a3-b5cc-7f710d9835fc)

### Kafka Cluster

![architecture](https://github.com/jongwook123/Newreka/assets/122584199/88a94f8e-5bed-47c7-b621-bfdafa03e578)

### Elasticsearch Cluster

![Untitled 3](https://github.com/jongwook123/Newreka/assets/122584199/504fc57e-3598-4f2b-9008-30b1099fd507)

## 📊 ERD

![Untitled 4](https://github.com/jongwook123/Newreka/assets/122584199/501d2869-cf8a-4809-a9cd-75266edf0324)

## ✌️ 팀원 소개

| 이름  | 역할              | 주요 임무           |
| --- | --------------- | ---------------------------------------------------------- |
| 김정환 | BE, DevOps      | 서버 구축, CI/CD 자동 배포, Nginx, 스크랩 기능                          |
| 윤우혁 | BE, Data, Infra | 아키텍쳐 설계, Elasticsearch, Logstash, Kibana, 핫토픽 선정, 연관 뉴스 선정 |
| 천병찬 | BE, Data        | ERD 설계, JPA, JWT, 계정 관리, 이메일 서비스, File Beat                |
| 채경호 | BE, Data        | JPA, 뉴스 크롤링, 요약 기능, 퀴즈 생성, 스크랩 기능                          |
| 박종욱 | FE, BE, Data    | UI/UX, 프론트엔드, 뉴스 크롤링, 핫토픽 선정, Kafka                        |
| 김선영 | FE, BE, Data    | UI/UX, 프론트엔드, Logstash                                     |
