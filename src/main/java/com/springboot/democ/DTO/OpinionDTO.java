package com.springboot.democ.DTO;

public class OpinionDTO {

    private String videokey;

    private int yesResponse2;

    private int noResponse2;

    private int total2;

    public OpinionDTO(){}

    public OpinionDTO(String videokey, int yesResponse2, int noResponse2, int total2) {
        this.videokey = videokey;
        this.yesResponse2 = yesResponse2;
        this.noResponse2 = noResponse2;
        this.total2 = total2;
    }


    public String getVideokey() {
        return videokey;
    }

    public void setVideokey(String videokey) {
        this.videokey = videokey;
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

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }
}
