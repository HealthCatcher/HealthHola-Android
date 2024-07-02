package com.hsfa.hearur_android.network;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NaverApiService {
    @GET("v1/nid/me")
    Call<NaverProfileResponse> getNaverUserProfile(
            @Header("Authorization") String accessToken
    );

}
