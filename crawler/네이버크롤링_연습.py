from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
import pandas as pd
# 웹 드라이버 설정
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)
service = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=chrome_options)

# 웹 페이지 열기
driver.get('https://naver.com/')

# 대기 시간 설정
wait = WebDriverWait(driver, 10)

# 대기 후 요소 찾기
shortcut_area = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, '#shortcutArea > ul')))
shortcut_area.find_element(By.CSS_SELECTOR, 'li:nth-child(5) > a').click()

driver.switch_to.window(driver.window_handles[-1])

# 다음 요소 클릭
main_sublnb = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, '#ct > div > section.main_content > div.main_sublnb > ul')))
main_sublnb.find_element(By.CSS_SELECTOR, 'li:nth-child(1) > a').click()

driver.switch_to.window(driver.window_handles[-1])


main_ = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, '#groupOfficeList > table > tbody')))
main_.find_element(By.CSS_SELECTOR, 'tr:nth-child(10) > td > ul > li > a').click()

# list_body 클래스 아래의 type06_headline 클래스 찾기
type06_headline = driver.find_element(By.CSS_SELECTOR, '.list_body .type06_headline')

# type06_headline 클래스 아래의 li 요소들 가져오기
li_elements_in_headline = type06_headline.find_elements(By.TAG_NAME, 'li')

# type06 클래스 아래의 li 요소들 가져오기
type06 = driver.find_element(By.CSS_SELECTOR, '.list_body .type06')
li_elements_in_type06 = type06.find_elements(By.TAG_NAME, 'li')


newsData = []

for element in li_elements_in_headline:
    element_html = element.get_attribute('outerHTML')
    soup = BeautifulSoup(element_html, 'html.parser')
    image_tag = soup.find('img')
    if image_tag:
        image_url = image_tag.get('src')
        title = image_tag.get('alt')
    else:
        image_url = 'No image available'
        title = soup.find('a', class_='nclicks(fls.list)').text.strip()
    # 필요한 정보 추출 및 정리
    article_url = soup.find('a', class_='nclicks(fls.list)')['href']
    writing = soup.find('span', class_='writing').text.strip()
    date = soup.find('span', class_='date').text.strip()


    try:
        article_data = {
            "Image URL": image_url,
            "Article URL": article_url,
            "Title": title,
            "Writing": writing,
            "Date": date,
        }


        newsData.append(article_data)

    except Exception as e:
        print(f"Error occurred while processing article: {str(e)}")


for element in li_elements_in_type06:
    element_html = element.get_attribute('outerHTML')
    soup = BeautifulSoup(element_html, 'html.parser')
    image_tag = soup.find('img')
    if image_tag:
        image_url = image_tag.get('src')
        title = image_tag.get('alt')
    else:
        image_url = 'No image available'
        title = soup.find('a', class_='nclicks(fls.list)').text.strip()
    # 필요한 정보 추출 및 정리
    article_url = soup.find('a', class_='nclicks(fls.list)')['href']
    writing = soup.find('span', class_='writing').text.strip()
    date = soup.find('span', class_='date').text.strip()

    try:

        article_data = {
            "Image URL": image_url,
            "Article URL": article_url,
            "Title": title,
            "Writing": writing,
            "Date": date,
        }

        newsData.append(article_data)

    except Exception as e:
        print(f"Error occurred while processing article: {str(e)}")

driver.quit()

file = pd.DataFrame(newsData)

file.to_csv('C:/Users/SSAFY/Desktop//news.csv', sep=',', na_rep='NaN', encoding="utf-8-sig")

