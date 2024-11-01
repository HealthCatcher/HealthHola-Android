package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentExperienceBinding;

public class ExperienceFragment extends Fragment {

    private FragmentExperienceBinding binding;
    private ExperienceViewModel experienceViewModel;
    private LinearLayout linearLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExperienceViewModel experienceViewModel =
                new ViewModelProvider(this).get(ExperienceViewModel.class);

        binding = FragmentExperienceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        linearLayout = binding.experienceList;

        //final TextView textView = binding.textExperience;
        //experienceViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel 인스턴스 가져오기
        experienceViewModel = new ViewModelProvider(this).get(ExperienceViewModel.class);

        // LiveData의 데이터를 구독하여 UI에 반영
        experienceViewModel.getDataList().observe(getViewLifecycleOwner(), strings -> {
            // 기존 뷰 제거 (중복 방지)
            linearLayout.removeAllViews();
            for (String item : strings) {
                // 커스텀 뷰 추가
                View itemView = getLayoutInflater().inflate(R.layout.experience_item3, linearLayout, false);

                // XML에서 정의된 뷰 요소들을 가져옴
                ImageView imageView = itemView.findViewById(R.id.card_image);
                TextView titleText = itemView.findViewById(R.id.card_title);
                TextView subtitleText = itemView.findViewById(R.id.card_subtitle);
                TextView contentText = itemView.findViewById(R.id.card_content);

                // 제목, 서브타이틀, 내용 설정 (임시 데이터)
                titleText.setText(item); // 제목 설정
                subtitleText.setText("Subtitle for " + item); // 서브타이틀 설정

                // 조회수와 추천수를 card_content에 표시
                int views = 1000; // 샘플 조회수
                int likes = 200;  // 샘플 추천수
                contentText.setText("조회 " + views + " 추천 " + likes); // 조회수와 추천수 설정

                // 이미지 설정 (임시 이미지)
                imageView.setImageResource(R.drawable.background); // 샘플 이미지 설정

                // LinearLayout에 추가
                linearLayout.addView(itemView);
            } 
        });
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}