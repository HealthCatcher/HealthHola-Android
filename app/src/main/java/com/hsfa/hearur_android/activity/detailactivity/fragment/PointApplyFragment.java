package com.hsfa.hearur_android.activity.detailactivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentPointApplyBinding;

public class PointApplyFragment extends Fragment {
    private FragmentPointApplyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPointApplyBinding.inflate(inflater, container, false);
        binding.customNavBar.centerIconBackground.setBackgroundResource(R.drawable.background_selected_circle_tab);
        // 필요한 경우 ViewModel 초기화
        return binding.getRoot();
    }
}
