package com.hsfa.hearur_android.activity.mainactivity.ui.survey;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hsfa.hearur_android.databinding.FragmentSurveyBinding;

import java.util.Arrays;
import java.util.List;

public class SurveyFragment extends Fragment {
    private FragmentSurveyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // FragmentSurveyBinding을 사용하여 View를 바인딩
        binding = FragmentSurveyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // 리사이클러뷰 설정
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        // 설문조사 데이터 (간단한 3개 예시)
        List<String> surveyList = Arrays.asList("설문 1: 건강 설문", "설문 2: 운동 습관", "설문 3: 음식 선호도");

        // 리사이클러뷰 어댑터 설정
        SurveyAdapter adapter = new SurveyAdapter(surveyList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
