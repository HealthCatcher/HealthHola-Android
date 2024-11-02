package com.hsfa.hearur_android.activity.detailactivity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // "신청하기" 버튼 클릭 리스너
        Button applyButton = rootView.findViewById(R.id.btn_apply);
        applyButton.setOnClickListener(v -> navigateToFragment(new ApplyFragment()));

        // "포인트 받고 신청하기" 버튼 클릭 리스너
        Button pointApplyButton = rootView.findViewById(R.id.btn_apply_with_points);
        pointApplyButton.setOnClickListener(v -> navigateToFragment(new PointApplyFragment()));

        return rootView;
    }

    // 프래그먼트 전환 메서드
    private void navigateToFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_detail_container, fragment) // fragment_container는 activity_detail.xml에서 지정한 FrameLayout의 ID
                .addToBackStack(null)
                .commit();
    }
}
