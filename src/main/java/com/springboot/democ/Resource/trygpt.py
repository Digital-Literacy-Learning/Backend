# -*- coding: utf-8 -*-


from youtube_transcript_api import YouTubeTranscriptApi
import openai
import sys
from youtube_transcript_api import YouTubeTranscriptApi


openai.api_key =




def question(article):
    messages = []

    messages.append({"role":"user", "content": f"{article}"})

    completion = openai.ChatCompletion.create(
        model = "gpt-3.5-turbo",
        messages = messages
    )

    return completion.choices[0].message["content"].strip()

# 현재 조회 중인 뉴스의 자막을 추출해서
# 사실과 의견 구분 요청

def gpt(videokey):

    transcript=YouTubeTranscriptApi.get_transcript(f"{videokey}" , languages=['ko','ko'])


    subtitle = ""
    for i in transcript:
        subtitle += i['text']


    #기사의 형식이 아닌경우도 있어, 일차적으로 기사의 형식으로 변환해달라 요청합니다.
    change_to_article = subtitle + "해당 내용을 기사의 형식으로 바꿔줘."


    result1 = question(change_to_article)
    #print( "result1: " f"{result1}")
    #그 후 사실과 의견의 구분을 요청합니다.
    fact_and_opinion = result1 + " 사실과 의견의 구분 : 기사 내용 중 어디가 사실이고 어디가 의견인지 구분할 수 있나요? 이를 구분하여 알려주세요"
    result2 = question(fact_and_opinion)
    #print("result2: " f"{result2}")

    return result2

article = sys.argv[1:]


result = [gpt(article) for article in article]
result_combined = '\n'.join(result)
print(result_combined)