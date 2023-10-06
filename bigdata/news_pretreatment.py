from konlpy.tag import Komoran
import re
from datetime import datetime, timedelta
import os
import string
from collections import Counter
import math
import mysql.connector
from apscheduler.schedulers.blocking import BlockingScheduler

komoran = Komoran()

# 정규화
def preprocess(text):
    text=text.strip()  
    text=re.compile('<.*?>').sub('', text) 
    text = re.compile('[%s]' % re.escape(string.punctuation)).sub(' ', text)  
    text = re.sub('\s+', ' ', text)  
    text = re.sub(r'\[[0-9]*\]',' ',text) 
    text=re.sub(r'[^\w\s]', ' ', str(text).strip())
    text = re.sub(r'\d',' ',text) 
    text = re.sub(r'\s+',' ',text) 
    return text

# 명사/영단어 추출, 한글자 제외, 불용어 제거
def final(text, stopwords):
    n = []
    word = komoran.nouns(text)
    p = komoran.pos(text)
    for pos in p:
      if pos[1] in ['SL']:
        word.append(pos[0])
    for w in word:
      if len(w)>1 and w not in stopwords:
        n.append(w)
    #return " ".join(n)
    return n

def finalpreprocess(text, stopwords):
  return final(preprocess(text), stopwords)

def hottopic():
    # 불용어 리스트
    stopword_file = open('/home/ubuntu/stopwords.txt', 'r', encoding='utf-8')
    stopwords = []
    for word in stopword_file.readlines():
        stopwords.append(word.rstrip())
    stopword_file.close()

    # 데이터 읽기
    cur_news_data_list = []
    # 현재 시간에서 10분 단위로 내림하여 설정
    now = datetime.now()
    #now = datetime(2023, 9, 25, 10, 00)
    now_minutes_floor = (now.minute // 10) * 10
    now = now.replace(minute=now_minutes_floor)
    
    start_time = now - timedelta(hours=3, minutes = 10)
    print("데이터시작: " + str(start_time.strftime("%Y%m%d %H%M")))
    
    while start_time < now:
        file_path = "/home/ubuntu/news_title/{date}/{hour}/news_title_{date}_{time}.txt".format(
            date=start_time.strftime("%Y%m%d"),
            hour=start_time.strftime("%H"),
            time=start_time.strftime("%H%M")
        )
        
        # 데이터 읽고 추가
        with open(file_path, 'r', encoding='utf-8') as cur_news_file:
            cur_news_data_list.extend(line.rstrip() for line in cur_news_file)
        
        # 10분 단위
        start_time += timedelta(minutes=10)
    print("데이터종료: " + str(start_time.strftime("%Y%m%d %H%M")))

    # 제목 띄워쓰기로 합치기
    cur_news_data = ' '.join(cur_news_data_list)

    # 데이터 전처리
    processed_news_data = finalpreprocess(cur_news_data, stopwords)
    
    # 빈도수 계산
    freq_dist = Counter()

    time_index = 0    # 시간 인덱스 (0부터 시작)
    time_decay_rate = 0.1    # 시간 경과에 따른 감소율
    
    for news_title in reversed(cur_news_data_list):
        processed_words_in_title=set(final(preprocess(news_title), stopwords))
       
        for word in processed_words_in_title:
            freq_dist[word] += time_index * time_decay_rate
    
        time_index += 1   # 다음 뉴스로 넘어갈 때마다 시간 인덱스 증가
    
    # 모든 키워드 저장
    all_keywords = freq_dist.most_common()
    
    top_10_keywords = []
    index = 0
    
    # 제목 단어 집합을 생성 (중복 제거를 위한 작업)
    cur_news_data_set_list = [set(title.split(' ')) for title in cur_news_data_list]
    
    while len(top_10_keywords) < 10:
        current_keyword = all_keywords[index][0]
        
        # Check if keyword is already covered by other keywords in top_10_keywords
        if not any(current_keyword in news_title_set for news_title_set in cur_news_data_set_list for keyword, _ in top_10_keywords if keyword in news_title_set):
            top_10_keywords.append(all_keywords[index])
            
        index += 1
    
    print(top_10_keywords)
    return top_10_keywords


# 데이터 베이스 저장
def save(top_10_keywords):

    db = mysql.connector.connect(
      host="43.202.33.232",
      user="d103",
      password="1234",
      database="newreka"
    )
    
    cursor = db.cursor()
    
    try:
        now = datetime.now()
        now_minutes_floor = (now.minute // 10) * 10
        floored_time = now.replace(minute=now_minutes_floor, second=0, microsecond=0)
        
        sql_insert_time_query = """INSERT INTO time (time) VALUES (%s)"""
        insert_time_value = (floored_time,)

        with open('sql_queries.sql', 'a') as f:
            f.write(f"{sql_insert_time_query % insert_time_value};\n")

        cursor.execute(sql_insert_time_query, insert_time_value)
    
        last_inserted_id = cursor.lastrowid
        
        db.commit()

    except mysql.connector.Error as error:
       print(f"Failed to insert into MySQL table {error}")
       

    try:
       for keyword_name, _ in top_10_keywords:
           sql_insert_key_word_query = """INSERT INTO key_word (name, summary, time_id) VALUES (%s, %s, %s)"""
           
           insert_key_word_values = (keyword_name[:30], "", last_inserted_id)
        
           with open('sql_queries.sql', 'a') as f:
               f.write(f"{sql_insert_key_word_query % insert_key_word_values};\n")
    
           cursor.execute(sql_insert_key_word_query, insert_key_word_values)
           
       db.commit()
    
    except mysql.connector.Error as error:
       print(f"Failed to insert into MySQL table {error}")
    
    finally:
         if db.is_connected():
             cursor.close()
             db.close()

# 배치 작업
def batch_jobs():
    top_10_keywords = hottopic()
    save(top_10_keywords)


if __name__ == '__main__':

    sched = BlockingScheduler()

    # 매 시간 '00~50' 분에 batch_jobs() 함수 실행
    sched.add_job(batch_jobs, 'cron', minute='0')
    sched.add_job(batch_jobs, 'cron', minute='10')
    sched.add_job(batch_jobs, 'cron', minute='20')
    sched.add_job(batch_jobs, 'cron', minute='30')
    sched.add_job(batch_jobs, 'cron', minute='40')
    sched.add_job(batch_jobs, 'cron', minute='50')
    
    sched.start()
             
