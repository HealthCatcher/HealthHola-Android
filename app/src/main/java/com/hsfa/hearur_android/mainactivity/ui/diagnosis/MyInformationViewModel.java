package com.hsfa.hearur_android.mainactivity.ui.diagnosis;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyInformationViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public MyInformationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
