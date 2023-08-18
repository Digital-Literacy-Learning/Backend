package com.springboot.democ.Controller;
public class VideoRequest {

    private String videokey;

    public VideoRequest(){}

    public VideoRequest(String videokey) {
        this.videokey = videokey;
    }

    public String getVideokey() {
        return videokey;
    }

    public void setVideokey(String videokey) {
        this.videokey = videokey;
    }
}
