package com.springboot.democ.Controller;


import java.beans.ConstructorProperties;

public class UrlRequest {

    private String url;

    @ConstructorProperties({"url"})
    public UrlRequest(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

