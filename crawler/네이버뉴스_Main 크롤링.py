import requests
from bs4 import BeautifulSoup
from datetime import datetime
import pandas as pd
newsData = []
for i in range(950):
    print(i)
    url = f"https://news.naver.com/main/list.naver?mode=LSD&mid=sec&sid1=001&date=20230907&page={str(i)}"
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36"}

    res = requests.get(url, headers=headers)
    soup = BeautifulSoup(res.text, 'lxml')
    news3 = soup.select(".list_body")


    lis3 = news3[0].findAll("li")

    for li in lis3:

    # 뉴스링크와 제목
        a = li.findAll("a")
        # list_title = li.select_one(".nclicks(fls.list)")
        if(len(a)==2):
            news_title = a[1].text.replace("\n","").replace("\t","")
            news_link = a[1].get("href")
        else:
            news_title = a[0].text.replace("\n","").replace("\t","")
            news_link = a[0].get("href")
        # 뉴스 썸네일
        try:news_img = li.select_one("img").get("src")
        except: news_img = None

        # 저장
        newsData.append({
            'title': news_title,
            'link': news_link,
            'img': news_img
        })

for news in newsData:
    news_url = news['link']
    res = requests.get(news_url, headers=headers)
    soup = BeautifulSoup(res.text, 'lxml')
    news_time = soup.select_one(".media_end_head_info_datestamp").select_one(".media_end_head_info_datestamp_time").get("data-date-time")
    news_content = soup.select_one("#newsct_article").text.replace("\n","").replace("\t","")
    news['time'] = news_time
    news['contents'] = news_content


file = pd.DataFrame(newsData)

file.to_csv('C:/Users/SSAFY/Desktop//news.csv', sep=',', na_rep='NaN', encoding="utf-8-sig")