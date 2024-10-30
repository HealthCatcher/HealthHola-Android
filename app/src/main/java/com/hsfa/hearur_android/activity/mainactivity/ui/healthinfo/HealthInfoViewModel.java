package com.hsfa.hearur_android.activity.mainactivity.ui.healthinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HealthInfoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HealthInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragmenttest");
    }

    public LiveData<String> getText() {
        return mText;
    }
}