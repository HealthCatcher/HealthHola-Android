package com.hsfa.hearur_android.activity.mainactivity.ui.community;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public PostViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is post fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
