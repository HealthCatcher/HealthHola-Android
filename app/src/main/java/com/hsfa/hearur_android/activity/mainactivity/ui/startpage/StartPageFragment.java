package com.hsfa.hearur_android.activity.mainactivity.ui.startpage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.databinding.FragmentStartpageBinding;

public class StartPageFragment extends Fragment {
    private StartPageViewModel startPageViewModel;
    private FragmentStartpageBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
