package com.example.moviesapp.Domains;

import java.io.Serializable;

public class Cast implements Serializable {
    private String picUrl;
    private String actor;

    public Cast() {
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
