package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.detailactivity.DetailActivity;
import com.hsfa.hearur_android.databinding.FragmentExperienceBinding;

public class ExperienceFragment extends Fragment {

    private FragmentExperienceBinding binding;
    private ExperienceViewModel experienceViewModel;
    private FrameLayout frameLayout;
    private TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        tabLayout = view.findViewById(R.id.tab_layout);

        // 기본 프래그먼트 로드 (체험리스트)
        loadFragment(new ExperienceListFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new ExperienceListFragment();
                        break;
                    case 1:
                        selectedFragment = new RecommendationListFragment();
                        break;
//                    case 2:
//                        selectedFragment = new MyListFragment();
//                        break;
                    default:
                        selectedFragment = new ExperienceListFragment();
                }
                loadFragment(selectedFragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.experience_list, fragment);
        transaction.commit();
    }
}