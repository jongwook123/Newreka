import requests
from bs4 import BeautifulSoup

response = requests.get("https://www.naver.com/")
html = response.text
soup = BeautifulSoup(html, 'html.parser')
word = soup.select_one('title')
print(word.text)