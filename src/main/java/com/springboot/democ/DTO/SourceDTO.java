package com.springboot.democ.DTO;



public class SourceDTO {

    private String videokey;

    private int yesResponse1;

    private int noResponse1;

    private int total1;

    public SourceDTO(){}

    public SourceDTO(String videokey, int yesResponse1, int noResponse1, int total1) {
        this.videokey = videokey;
        this.yesResponse1 = yesResponse1;
        this.noResponse1 = noResponse1;
        this.total1 = total1;
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

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }
}
