package com.hsfa.hearur_android.network;

import com.google.gson.annotations.SerializedName;



public class FetchJWTResponse {
    @SerializedName("code")
    private String code;

    public String getCode() {
        return code;
    }
}
