package com.springboot.democ.Controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//요청에서 보낸 Body의 값을 받는 부분
public class ResponseRequest {

    private String userId;

    private String videokey;

    private String response1;

    private String response2;

    private String category;

    private String publisher;

    private String postDate;

    private String gpt;

    public ResponseRequest(String userId, String videokey, String response1, String response2, String category, String publisher, String postDate, String gpt) {
        this.userId = userId;
        this.videokey = videokey;
        this.response1 = response1;
        this.response2 = response2;
        this.category = category;
        this.publisher = publisher;
        this.postDate = postDate;
        this.gpt = gpt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVideokey() {
        return videokey;
    }

    public void setVideokey(String videoKey) {
        this.videokey = videoKey;
    }

    public String getResponse1() {
        return response1;
    }

    public void setResponse1(String response1) {
        this.response1 = response1;
    }

    public String getResponse2() {
        return response2;
    }

    public void setResponse2(String response2) {
        this.response2 = response2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getGpt() {
        return gpt;
    }

    public void setGpt(String gpt) {
        this.gpt = gpt;
    }



    public LocalDate getFormattedPostDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(postDate, formatter);
    }
}