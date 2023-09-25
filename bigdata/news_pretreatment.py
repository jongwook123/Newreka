from konlpy.tag import Okt
from konlpy.tag import Komoran
import re
from datetime import datetime
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
    cur_news_data = []
    # 첫 번째 반복문: 21부터 23까지 (3시간)
    for hour in range(20, 21):
    
        # 두 번째 반복문: 각 시간대에 대해 파일 읽기 (00부터 50까지)
        for minute in range(0, 51, 10):
            file_name = "/home/ubuntu/news_title/20230922/{:02d}/news_title_20230922_{:02d}{:02d}.txt".format(hour, hour, minute)
            with open(file_name, 'r', encoding='utf-8') as cur_news_file:
                for word in cur_news_file.readlines():
                    cur_news_data.append(word.rstrip())


    # 제목 띄워쓰기로 합치기
    cur_news_data = ' '.join(cur_news_data)

    # 데이터 전처리
    processed_news_data = finalpreprocess(cur_news_data)
    #print(processed_news_data)

    # Compute frequency distribution
    freq_dist = Counter(processed_news_data)

    # Print the top-10 words
    top_10_keywords = freq_dist.most_common(10)
    print(top_10_keywords)

