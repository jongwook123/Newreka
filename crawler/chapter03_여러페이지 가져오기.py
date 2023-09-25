import requests
from bs4 import BeautifulSoup
import pyautogui

headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"}


# for i in range(1,30,10):
#     print(f"{pageNum}페이지 입니다.-----------------")
#     response = requests.get(f'https://search.naver.com/search.naver?where=news&sm=tab_jum&query={keyword}&start={i}')
#     html = response.text
#     soup = BeautifulSoup(html,'html.parser')
#     links = soup.select(".news_tit") # 결과는 리스트
#     for link in links:
#         title = link.text # 태그 안에 텍스트요소를 가져온다
#         url = link.attrs['href'] # href의 속성값을 가져온다
#         print(title,url)
#     pageNum += 1
pageNum = 1
for i in range(1,2):
    print(f"{pageNum}페이지 입니다.-----------------")
    response = requests.get(f'https://news.naver.com/main/list.naver?mode=LSD&mid=sec&sid1=001&date=20230907&page=1', headers=headers)
    html = response.text
    soup = BeautifulSoup(html,'html.parser')
    news = []
    for s in range(1, 11):  # 1부터 11까지 반복
        news1 = soup.select(f"#main_content > div.list_body.newsflash_body > ul.type06_headline > li:nth-child({s})")
        news.append(news1)
    print(news)
        

    pageNum += 1