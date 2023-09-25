from konlpy.tag import Okt
from konlpy.tag import Komoran
import re
from datetime import datetime, timedelta
import os
import string
import pandas as pd
from collections import Counter

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
    # 현재시간 가져오기
    # 2023-09-22 00:53:10.181418
    now = datetime.now()

    # 폴더 생성
    directory = '/home/ubuntu/bigdata'
    # createFolder(directory_path)

    try:
        # 해당 경로에 폴더가 존재하지 않으면 폴더 생성
        if not os.path.exists(directory):
            os.makedirs(directory)
    except OSError:
        print ('Error: Creating directory. ' +  directory)

    # 년월일시 2023092213 : 2023년 09월 22일 13시
    file_date = now.strftime("%Y%m%d%H")
    file_name_template = file_date

    # 한 시간 단위
    start_time = str(now.strftime('%Y-%m-%d %H')) + ":00:00"

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
    now_minutes_floor = (now.minute // 10) * 10
    now = now.replace(minute=now_minutes_floor)
    
    start_time = now - timedelta(hours=3, minutes = 10)
    
    while start_time < now:
        file_path = "/home/ubuntu/news_title/{date}/{hour}/news_title_{date}_{time}.txt".format(
            date=start_time.strftime("%Y%m%d"),
            hour=start_time.strftime("%H"),
            time=start_time.strftime("%H%M")
        )
        #print(file_path)
        
        # 데이터 읽고 추가
        with open(file_path, 'r', encoding='utf-8') as cur_news_file:
            cur_news_data_list.extend(line.rstrip() for line in cur_news_file)
        
        # 10분 단위
        start_time += timedelta(minutes=10)


    # 제목 띄워쓰기로 합치기
    cur_news_data = ' '.join(cur_news_data_list)

    # 데이터 전처리
    processed_news_data = finalpreprocess(cur_news_data)
    #print(processed_news_data)

    # Compute frequency distribution
    freq_dist = Counter(processed_news_data)

    # Print the top-10 words
    top_10_keywords = freq_dist.most_common(10)
    print(top_10_keywords)
    
    # KeyBert 모델 초기화
    #model = KeyBERT('distilbert-base-nli-mean-tokens')

    # 대표 키워드 추출하기
    #top_10_keywords_with_scores= model.extract_keywords(processed_news_data, keyphrase_ngram_range=(1,2), stop_words=None, use_maxsum=True,nr_candidates=20,top_n=10)

    #print(top_10_keywords_with_scores)

