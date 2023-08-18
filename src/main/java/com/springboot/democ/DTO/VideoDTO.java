package com.springboot.democ.DTO;

import java.time.LocalDate;

public class VideoDTO {

    private int id;
    private String userId;

    private String videokey;

    private int yesResponse1;

    private int noResponse1;

    private int yesResponse2;

    private int noResponse2;

    private String gpt;

    private String category;

    private String publisher;

    private LocalDate postDate;


    public VideoDTO(int id, String userId, String videokey, int yesResponse1, int noResponse1, int yesResponse2, int noResponse2, String gpt, String category, String publisher, LocalDate postDate) {
        this.id = id;
        this.userId = userId;
        this.videokey = videokey;
        this.yesResponse1 = yesResponse1;
        this.noResponse1 = noResponse1;
        this.yesResponse2 = yesResponse2;
        this.noResponse2 = noResponse2;
        this.gpt = gpt;
        this.category = category;
        this.publisher = publisher;
        this.postDate = postDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setVideokey(String videokey) {
        this.videokey = videokey;
    }

    public int getYesResponse1() {
        return yesResponse1;
    }

    public void setYesResponse1(int yesResponse1) {
        this.yesResponse1 = yesResponse1;
    }

    public int getNoResponse1() {
        return noResponse1;
    }

    public void setNoResponse1(int noResponse1) {
        this.noResponse1 = noResponse1;
    }

    public int getYesResponse2() {
        return yesResponse2;
    }

    public void setYesResponse2(int yesResponse2) {
        this.yesResponse2 = yesResponse2;
    }

    public int getNoResponse2() {
        return noResponse2;
    }

    public void setNoResponse2(int noResponse2) {
        this.noResponse2 = noResponse2;
    }

    public String getGpt() {
        return gpt;
    }

    public void setGpt(String gpt) {
        this.gpt = gpt;
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

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }
}
