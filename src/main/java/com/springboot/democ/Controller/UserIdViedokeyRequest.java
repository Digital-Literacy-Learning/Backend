package com.springboot.democ.Controller;

public class UserIdViedokeyRequest {
    private String userId;

    private String videokey;

    public UserIdViedokeyRequest(String userId, String videokey) {
        this.userId = userId;
        this.videokey = videokey;
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
}
