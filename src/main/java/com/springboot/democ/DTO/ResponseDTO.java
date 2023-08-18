package com.springboot.democ.DTO;


import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {

    private String videoKey;

    private int yesResponse1;
    private int noResponse1;
    private int yesResponse2;
    private int noResponse2;
    private int total;
    private int total2;

    public ResponseDTO(){}

    public ResponseDTO( String videoKey , int yesResponse1, int noResponse1, int yesResponse2, int noResponse2, int total, int total2) {
        this.videoKey = videoKey;
        this.yesResponse1 = yesResponse1;
        this.noResponse1 = noResponse1;
        this.yesResponse2 = yesResponse2;
        this.noResponse2 = noResponse2;
        this.total = total;
        this.total2 = total2;
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


}
