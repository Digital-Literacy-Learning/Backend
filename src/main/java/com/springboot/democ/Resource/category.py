import sys
import requests
from bs4 import BeautifulSoup
import json


def get_category_from_url(url): 
    #영상 카테고리 찾기
    r = requests.get(url).text
    soup = BeautifulSoup(r, 'lxml') #url에서 html 파일 추출
    a=soup.find("meta", itemprop="genre")["content"] #영상 카테고리 추출
    
    r2 = requests.get(url).text
    soup2 = BeautifulSoup(r2, 'html.parser')  
    a2=soup2.find("link", itemprop="name")["content"]
   
    r3 = requests.get(url).text
    soup3 = BeautifulSoup(r3, 'html.parser')  
    a3=soup3.find("meta", itemprop="uploadDate")["content"]
   

    categoryData = {'category' : a , 'publisher' : a2 , 'datePublished' : a3}
    json_categoryData = json.dumps(categoryData, ensure_ascii=False)


    return json_categoryData


article = sys.argv[1:]


result = [get_category_from_url(article) for article in article]
result_combined = '\n'.join(result)
print(result_combined)

