package com.hsfa.hearur_android.mainactivity.ui.diagnosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.databinding.FragmentDiagnosisBinding;

public class DiagnosisFragment extends Fragment {
    private FragmentDiagnosisBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiagnosisViewModel diagnosisViewModel =
                new ViewModelProvider(this).get(DiagnosisViewModel.class);

        binding = FragmentDiagnosisBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDiagnosis;
        diagnosisViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}