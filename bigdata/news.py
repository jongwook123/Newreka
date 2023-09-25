import requests
from bs4 import BeautifulSoup
from datetime import datetime
import pandas as pd
import time
import schedule
import json
import requests
from bs4 import BeautifulSoup
import pandas as pd
from pytz import timezone
import os

newsData = []
newsTitle = []
scraped_urls = set()  # 고유한 스크랩된 URL을 저장하기 위한 세트
seoul_timezone = timezone('Asia/Seoul')
current_time = datetime.now(seoul_timezone).strftime("%Y%m%d_%H")

c_time = datetime.now(seoul_timezone)
rounded_minute = (c_time.minute // 10) * 10
current_time_10min = c_time.replace(minute=rounded_minute, second=0, microsecond=0)
current_time_minute = current_time_10min.strftime("%Y%m%d_%H%M")

today = datetime.now(seoul_timezone).strftime("%Y%m%d")

def scrape_and_save_news():
    global newsData
    global current_time
    global newsTitle
    today = datetime.now(seoul_timezone).strftime("%Y%m%d")
    current_time = datetime.now(seoul_timezone).strftime("%Y%m%d_%H")
    
    
    c_time = datetime.now(seoul_timezone)
    rounded_minute = (c_time.minute // 10) * 10
    current_time_10min = c_time.replace(minute=rounded_minute, second=0, microsecond=0)
    current_time_minute = current_time_10min.strftime("%Y%m%d_%H%M")
    
    # 1시간 단위 
    if current_time != scrape_and_save_news.previous_time:
        newsData = []
        scrape_and_save_news.previous_time = current_time
        print('here')
    
    # 10분단위 
    if current_time_minute != scrape_and_save_news.previous_time_minute:
        newsTitle = []
        scrape_and_save_news.previous_time_minute = current_time_minute
        print('here')
    
        
        
    a = 0
    for i in range(1,20):
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
                news_title = a[1].text.replace("\n", "").replace("\t", "").replace(",", "")
                news_link = a[1].get("href")
            else:
                news_title = a[0].text.replace("\n", "").replace("\t", "").replace(",", "")
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
                if news_writing[:2] == 'AP' or news_writing[:3] == 'EAP' or news_writing[:3] == 'EPA':
                    break
            except:
                news_writing = None
            
            
            news_title1 = news_title.replace("’","").replace("/","").replace("[","").replace("]","").replace(".","").replace("…","").replace('"', '').replace("‘","").replace("'","").replace("“","").replace("”","").replace("〔","").replace("〕","").replace("(","").replace(")","")
            newsTitle.append(
                #'title' : news_title,
                news_title1
            )
            
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
            # time.sleep(2)
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
            
            
            if None in (news_type, news_time, news_content):
                # 최근에 추가된 데이터를 삭제하고 루프 종료
                newsData.pop()
                break
            else:
                # 모든 데이터가 유효하면 저장
                newsData[-1]['type'] = news_type
                newsData[-1]['time'] = news_time
                newsData[-1]['contents'] = news_content
            

scrape_and_save_news.previous_time = datetime.now(seoul_timezone).strftime("%Y%m%d_%H")
scrape_and_save_news.previous_time_minute = datetime.now(seoul_timezone).strftime("%Y%m%d_%H%M")


schedule.every(10).seconds.do(scrape_and_save_news)

while True:
    schedule.run_pending()
    # 데이터 처리
    file = pd.DataFrame(newsData)
    file.to_csv(f'/home/ubuntu/news/news_{current_time}.csv', sep=',', na_rep='NaN', encoding="utf-8-sig",index=False)
    # file_title = pd.DataFrame(newsTitle)
    # file_title.to_csv(f'/home/ubuntu/news_title/news_title_{current_time_minute}.csv',sep=',', na_rep='NaN', encoding="utf-8-sig",index=False)
    
    c_time = datetime.now(seoul_timezone)
    rounded_minute = (c_time.minute // 10) * 10
    current_time_10min = c_time.replace(minute=rounded_minute, second=0, microsecond=0)
    current_time_minute = current_time_10min.strftime("%Y%m%d_%H%M")
    today = datetime.now(seoul_timezone).strftime("%Y%m%d")
    
    # 폴더가 없으면 생성
    folder_path = f'/home/ubuntu/news_title/{today}/{current_time_10min.strftime("%H")}'
    os.makedirs(folder_path, exist_ok=True)
    file_path = f'{folder_path}/news_title_{current_time_minute}.txt'

    # 파일에 뉴스 제목 추가
    with open(file_path, 'a', encoding='utf-8') as file:
        for title in newsTitle:
            file.write(str(title) + '\n')

    newsTitle = []
    time.sleep(1)



