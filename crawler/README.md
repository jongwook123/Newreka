# Crawling

## 0907

네이버뉴스 url을 통해 BeautifulSoup를 사용하여 크롤링

- 상위 태그로 영역을 불러 온 후 하위 영역의 정보 추출

- 기본적으로 class로 접근하되, 불가능할 시 기본 태그로 접근

Selenium을 사용하지 않고 BeautifulSoup를 사용한 이유

- 해당 페이지가 동적이지 않았고 많은 양의 데이터를 처리할때 
  BeautifulSoup가 더 빠른 작업속도를 가짐
