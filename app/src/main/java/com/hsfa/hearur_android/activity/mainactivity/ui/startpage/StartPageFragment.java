package com.hsfa.hearur_android.activity.mainactivity.ui.startpage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.activity.mainactivity.ui.experience.ExperienceViewModel;
import com.hsfa.hearur_android.databinding.FragmentExperienceBinding;
import com.hsfa.hearur_android.databinding.FragmentStartpageBinding;

public class StartPageFragment extends Fragment {
    private StartPageViewModel startPageViewModel;
    private FragmentStartpageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ViewModel 초기화
        startPageViewModel = new ViewModelProvider(this).get(StartPageViewModel.class);

        // ViewBinding 초기화
        binding = FragmentStartpageBinding.inflate(inflater, container, false);

        // 루트 뷰 반환
        return binding.getRoot();
    }
}

