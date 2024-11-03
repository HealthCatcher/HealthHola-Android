package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecommendationViewModel extends ViewModel {

    private final MutableLiveData<List<String>> recommendationList;

    public RecommendationViewModel() {
        // 더미 데이터를 생성하여 MutableLiveData에 설정
        recommendationList = new MutableLiveData<>();
        loadRecommendations();
    }

    // 추천 리스트에 더미 데이터 추가
    private void loadRecommendations() {
        List<String> dummyData = new ArrayList<>();
        dummyData.add("추천 상품 1");
        dummyData.add("추천 상품 2");
        dummyData.add("추천 상품 3");
        dummyData.add("추천 상품 4");
        dummyData.add("추천 상품 5");

        // 더미 데이터를 MutableLiveData에 설정
        recommendationList.setValue(dummyData);
    }

    // 외부에서 추천 리스트를 구독할 수 있도록 LiveData 반환
    public LiveData<List<String>> getRecommendationList() {
        return recommendationList;
    }
}
