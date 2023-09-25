import requests
from bs4 import BeautifulSoup
from datetime import datetime
import pandas as pd
import time
import schedule

import requests
from bs4 import BeautifulSoup
import pandas as pd

newsData = []
first_url = ""
today = datetime.today().date().strftime("%Y%m%d")
scraped_urls = set()  # 고유한 스크랩된 URL을 저장하기 위한 세트



def scrape_and_save_news():
    global newsData
    global first_url
    a = 0
    for i in range(1,30):
        if a == 1:
            break
        url = f"https://news.naver.com/main/list.naver?mode=LSD&mid=sec&sid1=001&date={today}&page={str(i)}"
        headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"
        }

        res = requests.get(url, headers=headers)
        soup = BeautifulSoup(res.text, 'lxml')
        news3 = soup.select(".list_body")

        lis3 = news3[0].findAll("li")

        for li in lis3:
            # 뉴스링크와 제목
            a = li.findAll("a")
            if len(a) == 2:
                news_title = a[1].text.replace("\n", "").replace("\t", "")
                news_link = a[1].get("href")
            else:
                news_title = a[0].text.replace("\n", "").replace("\t", "")
                news_link = a[0].get("href")

            # 고유한 URL인지 확인하고, 이미 스크랩된 URL이면 루프를 종료합니다.
            if news_link in scraped_urls:
                a = 1
                break
            else:
                scraped_urls.add(news_link)

            # 뉴스 썸네일
            try:
                news_img = li.select_one("img").get("src")
            except:
                news_img = None

            # 언론사
            try:
                news_writing = li.select_one(".writing").text
            except:
                news_writing = None

            # 저장
            newsData.append({
                'title': news_title,
                'link': news_link,
                'img': news_img,
                'writing': news_writing,
            })

            news_url = news_link

            res = requests.get(news_url, headers=headers)
            soup = BeautifulSoup(res.text, 'lxml')
            try:
                news_time = soup.select_one(".media_end_head_info_datestamp").select_one(".media_end_head_info_datestamp_time").get("data-date-time")
            except AttributeError:
                news_time = None

            news_content = soup.select_one("#newsct_article")
            if news_content:
                news_content = news_content.text.replace("\n", "").replace("\t", "")
            else:
                news_content = None

            news_type = soup.select_one(".media_end_categorize_item")
            if news_type:
                news_type = news_type.text
            else:
                news_type = None
            newsData[-1]['type'] = news_type
            newsData[-1]['time'] = news_time
            newsData[-1]['contents'] = news_content

            print(len(newsData))



schedule.every(20).seconds.do(scrape_and_save_news)

while True:
    schedule.run_pending()
    # 데이터 처리
    file = pd.DataFrame(newsData)
    file.to_csv('C:/Users/SSAFY/Desktop/news/news.csv', sep=',', na_rep='NaN', encoding="utf-8-sig")
    time.sleep(1)


