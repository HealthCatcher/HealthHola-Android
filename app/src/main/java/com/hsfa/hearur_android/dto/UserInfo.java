package com.hsfa.hearur_android.dto;

public class UserInfo {
    private String provider;
    private String providerId;
    private String email;
    private String name;

    public UserInfo() {
    }

    public UserInfo(String provider, String providerId, String email, String name) {
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.name = name;
    }

    public void setUserInfo(String provider, String providerId, String email, String name) {
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
        this.name = name;
    }
    public String getProvider() {
        return provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
