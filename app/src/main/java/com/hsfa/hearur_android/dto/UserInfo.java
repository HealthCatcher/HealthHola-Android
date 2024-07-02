package com.hsfa.hearur_android.dto;

public class UserInfo {
    private String provider;
    private String accessToken;

    public UserInfo() {
    }

    public UserInfo(String provider, String accessToken) {
        this.provider = provider;
        this.accessToken = accessToken;
    }
}
