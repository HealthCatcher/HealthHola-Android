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
                new Post("오늘의 운동 추천", "2024-10-13 13:14"),
                new Post("제목2", "내용2"),
                new Post("제목3", "내용3"),
                new Post("제목4", "내용4"),
                new Post("제목5", "내용5"),
                new Post("제목6", "내용6"),
                new Post("제목7", "내용7"),
                new Post("제목8", "내용8")

        ));
    }

    public LiveData<List<Post>> getPostList() {
        return posts;
    }
}
