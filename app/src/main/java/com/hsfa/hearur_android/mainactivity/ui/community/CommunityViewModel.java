package com.hsfa.hearur_android.mainactivity.ui.community;

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
                new Post("제목1", "내용1"),
                new Post("제목2", "내용2"),
                new Post("제목3", "내용3"),
                new Post("제목4", "내용4"),
                new Post("제목5", "내용5")
        ));
    }

    public LiveData<List<Post>> getPostList() {
        return posts;
    }
}
