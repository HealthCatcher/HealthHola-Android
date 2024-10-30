package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExperienceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<List<String>> dataList;
    public ExperienceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public LiveData<List<String>> getDataList() {
        if(dataList == null){
            dataList = new MutableLiveData<>();
            loadData();
        }
        return dataList;
    }
    public void loadData(){
        List<String> dummyData = new ArrayList<>();
        dummyData.add("식품");
        dummyData.add("영양제");
        dummyData.add("기구");
        dummyData.add("등등등");
        dummyData.add("5");
        dummyData.add("6");
        dummyData.add("7");
        dummyData.add("8");
        dataList.setValue(dummyData);
    }
}