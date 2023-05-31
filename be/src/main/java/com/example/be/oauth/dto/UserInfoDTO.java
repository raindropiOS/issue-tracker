package com.example.be.oauth.dto;

import org.springframework.data.annotation.Id;

public class UserInfoDTO {

    private String id;

    private String nickname;
    private String imgUrl;

    public UserInfoDTO(String id, String nickname, String imgUrl) {
        this.id = id;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
