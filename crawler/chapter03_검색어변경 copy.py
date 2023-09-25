import requests
from bs4 import BeautifulSoup
import pyautogui
import time
keyword = pyautogui.prompt("검색어를 입력하세요>>")
response = requests.get(f'https://news.naver.com/main/list.naver?mode=LSD&mid=sec&sid1=002&date=20230907')
html = response.text
soup = BeautifulSoup(html,'html.parser')
time.sleep(5)
links = soup.select(".nclicks(fls.list)") # 결과는 리스트
for link in links:
    time.sleep(5)
    title = link.text # 태그 안에 텍스트요소를 가져온다
    url = link.attrs['href'] # href의 속성값을 가져온다
    print(title,url)