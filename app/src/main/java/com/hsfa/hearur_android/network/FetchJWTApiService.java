package com.hsfa.hearur_android.network;

import com.hsfa.hearur_android.dto.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FetchJWTApiService {
    @POST("auth/login")
    Call<FetchJWTResponse> getJWT(
            @Body UserInfo userInfo
    );
}
