package com.hsfa.hearur_android.loginactivity.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public LoginViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragmenttest");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
