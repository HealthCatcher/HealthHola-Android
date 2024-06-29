package com.hsfa.hearur_android.network;

import com.hsfa.hearur_android.dto.UserInfo;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NaverProfileRepository {

    private final NaverApiService naverApiService;

    public NaverProfileRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        naverApiService = retrofit.create(NaverApiService.class);
    }

    public Observable<UserInfo> requestNaverUserProfile(String accessToken) {
        return naverApiService.getNaverUserProfile("Bearer " + accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(profileResponse -> {
                    String userId = profileResponse.getResponse().getId();
                    String userEmail = profileResponse.getResponse().getEmail();
                    String userName = profileResponse.getResponse().getName();
                    return new UserInfo("naver", userId, userEmail, userName);
                });
    }
}
