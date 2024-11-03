package com.hsfa.hearur_android.activity.mainactivity.ui.community;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.hsfa.hearur_android.dto.Post;
import java.util.List;

public class CommunityViewModel extends ViewModel {

    private final MutableLiveData<List<Post>> posts;

    public CommunityViewModel() {
        posts = new MutableLiveData<>();
    }

    public void fetchPostList() {
        posts.setValue(List.of(
                new Post("오늘의 운동 추천", "유산소와 근력 운동을 함께 조합해보세요!", 245, 48, null ),
                new Post("헬스 초보자를 위한 가이드", "초보자를 위한 헬스장에서의 기본 운동 루틴 정리.", 180, 30, "healthimage"),
                new Post("식단 관리 꿀팁", "단백질 섭취량을 늘리고 탄수화물은 적당히!", 320, 60, "dietimage"),
                new Post("다이어트 성공 후기", "꾸준한 운동과 건강한 식단 덕에 목표 달성!", 410, 88, null),
                new Post("홈트레이닝 장비 추천", "효과적인 홈트를 위한 필수 장비 소개.", 125, 22, "hometraining"),
                new Post("러닝 효과를 높이는 방법", "러닝 시 호흡법과 페이스 조절이 중요해요.", 290, 55, null),
                new Post("헬스 초보가 주의해야 할 점", "과한 운동은 부상 위험이 있습니다. 적당히!", 110, 18, null),
                new Post("스트레칭의 중요성", "운동 전후 스트레칭은 부상 방지에 필수적!", 150, 33, null)
        ));
    }

    public LiveData<List<Post>> getPostList() {
        return posts;
    }
}
