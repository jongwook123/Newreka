from konlpy.tag import Komoran
import re
from datetime import datetime, timedelta
import os
import string
from collections import Counter
import math

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
def final(text):
    n = []
    word = komoran.nouns(text)
    p = komoran.pos(text)
    for pos in p:
      if pos[1] in ['SL']:
        word.append(pos[0])
    for w in word:
      if len(w)>1 and w not in stopword:
        n.append(w)
    #return " ".join(n)
    return n

def finalpreprocess(text):
  return final(preprocess(text))

if __name__ == '__main__':

    # 불용어 리스트
    stopword_file = open('/home/ubuntu/stopwords.txt', 'r', encoding='utf-8')
    stopword = []
    for word in stopword_file.readlines():
        stopword.append(word.rstrip())
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
    processed_news_data = finalpreprocess(cur_news_data)
    
    # 빈도수 계산
    freq_dist = Counter()

    time_index = 0    # 시간 인덱스 (0부터 시작)
    time_decay_rate = 0.1    # 시간 경과에 따른 감소율
    
    for news_title in reversed(cur_news_data_list):
        processed_words_in_title=set(final(preprocess(news_title)))
       
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

    
