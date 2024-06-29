package com.hsfa.hearur_android.mainactivity.ui.diagnosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.databinding.FragmentMyinformationBinding;

public class MyInformationFragment extends Fragment {
    private FragmentMyinformationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyInformationViewModel myInformationViewModel =
                new ViewModelProvider(this).get(MyInformationViewModel.class);

        binding = FragmentMyinformationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        final TextView textView = binding.textMyinformation;
        myInformationViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
         */
        return root;
    }
}
