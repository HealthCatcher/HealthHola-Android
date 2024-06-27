package com.hsfa.hearur_android.ui.healthinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.databinding.FragmentHealthinfoBinding;

public class HealthInfoFragment extends Fragment {

    private FragmentHealthinfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HealthInfoViewModel homeViewModel =
                new ViewModelProvider(this).get(HealthInfoViewModel.class);

        binding = FragmentHealthinfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHealthinfo;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}