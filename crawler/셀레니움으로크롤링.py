from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager

# 브라우저 꺼짐 방지
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)

service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=chrome_options)

# 이전 페이지 열기
driver.get("https://www.naver.com")

# 이전 페이지에서 요소 클릭
wait = WebDriverWait(driver, 10)
element = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#shortcutArea > ul > li:nth-child(5) > a")))
element.click()

# 새로 열린 창(윈도우) 핸들 가져오기
handles = driver.window_handles

# 새로 열린 창으로 전환
driver.switch_to.window(handles[-1])  # 가장 최근에 열린 창으로 전환

# 현재 열린 페이지의 URL 가져오기
new_page_url = driver.current_url

element = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#ct > div > section.main_content > div.main_sublnb > ul > li:nth-child(1) > a")))
element.click()

driver.switch_to.window(handles[-1])
new_page_url = driver.current_url

element = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "#groupOfficeList > table > tbody > tr:nth-child(10) > td > ul > li > a")))
element.click()

driver.switch_to.window(handles[-1])
new_page_url = driver.current_url
# 오늘꺼 실시간으로 가져오기

# for i in range(1,lastpage*10,10):
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