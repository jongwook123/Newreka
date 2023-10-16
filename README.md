# [ 뉴스의 재발견, 뉴레카 ]

## 📰 프로젝트 소개

**Newreka: 뉴스관련 실시간 핫토픽 키워드를 제공하는 온라인 플랫폼**

Newreka는 평소 뉴스 읽기를 버거워하던 사람들을 위해 제공되는 서비스입니다. 사람들에게 뉴스 진입 장벽을 낮추고, 시간과 공간의 제약 없이 더 많은 기회를 제공합니다. 또한, 뉴스를 읽었음을 문제를 통해 검증하고, 얼마나 많은 뉴스를 읽었는지에 대한 객관적 지표를 제공함니다.

## ⚙️ 기술 스택

### BackEnd

<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=black">
<img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=black">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=black">
<img src="https://img.shields.io/badge/JPA-61DAFB?style=for-the-badge&logo=JPA&logoColor=black">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=black">

<img src="https://img.shields.io/badge/logstash-005571?style=for-the-badge&logo=logstash&logoColor=white">
<img src="https://img.shields.io/badge/filebeat-yellow?style=for-the-badge&logo=filebeat&logoColor=white">
<img src="https://img.shields.io/badge/apachekafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white">
<img src="https://img.shields.io/badge/elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white">
<img src="https://img.shields.io/badge/kibana-005571?style=for-the-badge&logo=kibana&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=black">

### FrontEnd

<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">


### Infra

<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=black">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=black">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=black">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=black">


### Cooperation

<img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=black">
<img src="https://img.shields.io/badge/jirasoftware-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=black">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=black">

## 💡 주요 기능

---

### 시간대별 Keyword Top 10

![02__ED_95_AB_ED_86_A0_ED_94_BD](/uploads/b7237864f20a2e6df1520cbc40cac0bc/02__ED_95_AB_ED_86_A0_ED_94_BD.gif)

- 매시 정각부터 시작하여 10분마다 실행되는 작업
- 로직
  - 네이버 뉴스 크롤링 데이터를 3시간 단위로 처리하며, 10분 간격으로 sliding
  - 제목 데이터를 가공 후 Komoran을 이용해 형태소 분석
  - 시간에 따라 서로 다른 가중치를 두어 빈도수 측정 후 상위 10개 키워드 선정

### Keyword 관련뉴스 3줄요약 & 관련뉴스 기사

![03__ED_86_A0_ED_94_BD__EA_B4_80_EB_A0_A8__EC_9A_94_EC_95_BD__EB_B0_8F__EA_B8_B0_EC_82_AC](/uploads/6cfcb86dd00fd653401d97d8847d68ec/03__ED_86_A0_ED_94_BD__EA_B4_80_EB_A0_A8__EC_9A_94_EC_95_BD__EB_B0_8F__EA_B8_B0_EC_82_AC.gif)

- 매시 1분부터 시작하여 10분마다 실행되는 작업
- 관련 뉴스 선정은 Elasticsearch에 검색한 결과를 이용
  - Carrot2 플러그인의 Lingo 알고리즘에 의해 선택되는 유사도 높은 기사 5개를 선정
  - Nori 형태 소 분석기를 커스텀하여 적용해 한글 검색 문제 해결
- Naver OpenAPI를 이용해 자동화

### Keyword 관련뉴스 퀴즈

![04__ED_86_A0_ED_94_BD__EA_B4_80_EB_A0_A8__EB_AC_B8_EC_A0_9C__ED_92_80_EA_B8_B0](/uploads/d7472714c156fcccc9881859562d193a/04__ED_86_A0_ED_94_BD__EA_B4_80_EB_A0_A8__EB_AC_B8_EC_A0_9C__ED_92_80_EA_B8_B0.gif)

- 매시 2분부터 시작하여 10분마다 실행되는 작업
- OpenAI API를 이용해 자동화

### Keyword 뉴스 스크랩 및 리다이랙트

![06__EB_89_B4_EC_8A_A4__EC_8A_A4_ED_81_AC_EB_9E_A9__EB_B0_8F__EB_A6_AC_EB_8B_A4_EC_9D_B4_EB_A0_89_ED_8A_B8](/uploads/d9042ebb5040fe46efc778b21371f484/06__EB_89_B4_EC_8A_A4__EC_8A_A4_ED_81_AC_EB_9E_A9__EB_B0_8F__EB_A6_AC_EB_8B_A4_EC_9D_B4_EB_A0_89_ED_8A_B8.gif)

### 퀴즈관련 Keyword 수집

![05__ED_82_A4_EC_9B_8C_EB_93_9C__EC_88_98_EC_A7_91__EA_B8_B0_EB_8A_A5](/uploads/c1b6becf3ae6331c08800d754a0e77ce/05__ED_82_A4_EC_9B_8C_EB_93_9C__EC_88_98_EC_A7_91__EA_B8_B0_EB_8A_A5.gif)


### 구독자 이메일 발송 서비스

![07__EC_9D_B4_EB_A9_94_EC_9D_BC__EC_84_9C_EB_B9_84_EC_8A_A4](/uploads/8f26a6e42681b254b59e91281338989c/07__EC_9D_B4_EB_A9_94_EC_9D_BC__EC_84_9C_EB_B9_84_EC_8A_A4.gif)

### 데이터 시각화

![Untitled](/uploads/34c61d75d80518751c0d6562a9a18d65/Untitled.png)

- Kibana를 통해 실시간 데이터 시각화

## 📄 아키텍처 설계도

![Untitled_1](/uploads/c29a16e58648d96d41791559d820dff3/Untitled_1.png)

### Data Pipeline

![Untitled_2](/uploads/681837301b22bbfddd8c3ff9b168ab72/Untitled_2.png)

### Kafka Cluster

![architecture](/uploads/be90aade38b0400640225a2eba069940/architecture.png)

### Elasticsearch Cluster

![Untitled_3](/uploads/be47adbb6ae8f4480e177df528450efa/Untitled_3.png)

## 📊 ERD

![Untitled_4](/uploads/a71962be2bba1ea6cfa38d70193b895c/Untitled_4.png)

## ✌️ 팀원 소개

| 이름  | 역할              | 주요 임무           |
| --- | --------------- | ---------------------------------------------------------- |
| 김정환 | BE, DevOps      | 서버 구축, CI/CD 자동 배포, Nginx, 스크랩 기능                          |
| 윤우혁 | BE, Data, Infra | 아키텍쳐 설계, Elasticsearch, Logstash, Kibana, 핫토픽 선정, 연관 뉴스 선정 |
| 천병찬 | BE, Data        | ERD 설계, JPA, JWT, 계정 관리, 이메일 서비스, File Beat                |
| 채경호 | BE, Data        | JPA, 뉴스 크롤링, 요약 기능, 퀴즈 생성, 스크랩 기능                          |
| 박종욱 | FE, BE, Data    | UI/UX, 프론트엔드, 뉴스 크롤링, 핫토픽 선정, Kafka                        |
| 김선영 | FE, BE, Data    | UI/UX, 프론트엔드, Logstash                                     |
