package com.springboot.democ.Controller;

import org.apache.catalina.User;

public class UserIdRequest {

    public String userId;

    public UserIdRequest(){}

    public UserIdRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
