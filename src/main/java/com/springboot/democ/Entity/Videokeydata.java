package com.springboot.democ.Entity;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table(name="videokeydata")
public class Videokeydata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "videokey")
    public String videokey;

    @Column(name = "yes1")
    public int yesResponse1;

    @Column(name = "no1")
    public int noResponse1;

    @Column(name = "yes2")
    public int yesResponse2;

    @Column(name = "no2")
    public int noResponse2;

    @Column(name = "total1")
    public int total1;

    @Column(name = "total2")
    public int total2;


    public Videokeydata() {
    }

    public Videokeydata(String videokey, int yesResponse1, int noResponse1, int yesResponse2, int noResponse2, int total1, int total2) {
        this.videokey = videokey;
        this.yesResponse1 = yesResponse1;
        this.noResponse1 = noResponse1;
        this.yesResponse2 = yesResponse2;
        this.noResponse2 = noResponse2;
        this.total1 = total1;
        this.total2 = total2;
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
        this.total1 = this.yesResponse1 + this.noResponse1;
    }

    public int getNoResponse1() {
        return noResponse1;
    }

    public void setNoResponse1(int noResponse1) {
        this.noResponse1 = noResponse1;
        this.total1 = this.yesResponse1 + this.noResponse1;
    }

    public int getYesResponse2() {
        return yesResponse2;
    }

    public void setYesResponse2(int yesResponse2) {
        this.yesResponse2 = yesResponse2;
        this.total2 = this.noResponse2 + this.yesResponse2;
    }

    public int getNoResponse2() {
        return noResponse2;
    }

    public void setNoResponse2(int noResponse2) {
        this.noResponse2 = noResponse2;
        this.total2 = this.noResponse2 + this.yesResponse2;
    }

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }
}