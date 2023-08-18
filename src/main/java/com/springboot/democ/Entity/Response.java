package com.springboot.democ.Entity;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="userId")
    private String userId;

    @Column(name = "videokey")
    private String videokey;

    @Column(name = "yes1")
    private int YesResponse1;

    @Column(name = "no1")
    private int NoResponse1;

    @Column(name = "yes2")
    private int YesResponse2;

    @Column(name = "no2")
    private int NoResponse2;


    @Column(name="total1")
    private int total;

    @Column(name="total2")
    private int total2;

    @Column(columnDefinition = "LONGTEXT" , name = "gpt")
    private String gpt;

    @Column(name="category")
    private String category;

    @Column(name="publisher")
    private String publisher;

    @Column(name="postDate")
    private LocalDate postDate;


    public Response(){}


    public Response(int id, String userId, String videokey, int yesResponse1, int noResponse1, int yesResponse2, int noResponse2, int total, int total2 ,String gpt,String category,String publisher,LocalDate postDate) {

        this.id = id;
        this.userId = userId;
        this.videokey = videokey;
        this.YesResponse1 = yesResponse1;
        this.NoResponse1 = noResponse1;
        this.YesResponse2 = yesResponse2;
        this.NoResponse2 = noResponse2;
        this.total = total;
        this.total2 = total2;
        this.gpt = gpt;
        this.category = category;
        this.publisher = publisher;
        this.postDate = postDate;
    }

    public String getUserId(){return userId;}
    public void setUserId(String userId) {this.userId = userId;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideokey() {
        return videokey;
    }

    public void setVideokey(String videokey) {
        this.videokey = videokey;
    }

    public int getYesResponse1() {
        return YesResponse1;
    }

    public void setYesResponse1(int yesResponse) {
        this.YesResponse1 = yesResponse;
        this.total = this.YesResponse1 + this.NoResponse1;
    }

    public int getNoResponse1() {
        return NoResponse1;
    }

    public void setNoResponse1(int noResponse) {
        this.NoResponse1 = noResponse;
        this.total = this.YesResponse1+ this.NoResponse1;
    }

    public int getYesResponse2() {
        return YesResponse2;
    }

    public void setYesResponse2(int yesResponse2) {
        this.YesResponse2 = yesResponse2;
        this.total2 = this.YesResponse2 + this.NoResponse2;
    }

    public int getNoResponse2() {
        return NoResponse2;
    }

    public void setNoResponse2(int noResponse2) {
        this.NoResponse2 = noResponse2;
        this.total2 = this.YesResponse2 + this.NoResponse2;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
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

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", videokey='" + videokey + '\'' +
                ", YesResponse1=" + YesResponse1 +
                ", NoResponse1=" + NoResponse1 +
                ", YesResponse2=" + YesResponse2 +
                ", NoResponse2=" + NoResponse2 +
                ", total=" + total +
                ", total2=" + total2 +
                ", gpt='" + gpt + '\'' +
                ", category='" + category + '\'' +
                ", publisher='" + publisher + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}
