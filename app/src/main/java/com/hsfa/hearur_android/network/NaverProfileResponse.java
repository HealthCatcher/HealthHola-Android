package com.hsfa.hearur_android.network;

import com.google.gson.annotations.SerializedName;

public class NaverProfileResponse {
    @SerializedName("resultcode")
    private String resultCode;

    @SerializedName("message")
    private String message;

    @SerializedName("response")
    private ResponseData response;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseData getResponse() {
        return response;
    }

    public void setResponse(ResponseData response) {
        this.response = response;
    }

    public static class ResponseData {
        @SerializedName("id")
        private String id;

        @SerializedName("email")
        private String email;

        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
