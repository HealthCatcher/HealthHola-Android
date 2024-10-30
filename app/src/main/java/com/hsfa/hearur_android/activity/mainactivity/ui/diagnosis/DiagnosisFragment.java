package com.hsfa.hearur_android.activity.mainactivity.ui.diagnosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentDiagnosisBinding;

public class DiagnosisFragment extends Fragment {
    private FragmentDiagnosisBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DiagnosisViewModel diagnosisViewModel =
                new ViewModelProvider(this).get(DiagnosisViewModel.class);

        binding = FragmentDiagnosisBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button myInformationButton = binding.myInformationButton;
        myInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout diagnosisLayout = binding.diagnosisLayout;
                diagnosisLayout.removeAllViews();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MyInformationFragment myInformationFragment = new MyInformationFragment();
                transaction.replace(R.id.diagnosis_layout, myInformationFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        /*
        final TextView textView = binding.textDiagnosis;
        diagnosisViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
         */
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}