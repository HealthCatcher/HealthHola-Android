package com.hsfa.hearur_android.mainactivity.ui.experience;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.databinding.FragmentExperienceBinding;

public class ExperienceFragment extends Fragment {

    private FragmentExperienceBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExperienceViewModel experienceViewModel =
                new ViewModelProvider(this).get(ExperienceViewModel.class);

        binding = FragmentExperienceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textExperience;
        //experienceViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}