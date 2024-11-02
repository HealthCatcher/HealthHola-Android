package com.hsfa.hearur_android.activity.detailactivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.databinding.FragmentApplyBinding;

public class ApplyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentApplyBinding binding = FragmentApplyBinding.inflate(inflater, container, false);
        // 필요한 경우 ViewModel 초기화
        return binding.getRoot();
    }
}
