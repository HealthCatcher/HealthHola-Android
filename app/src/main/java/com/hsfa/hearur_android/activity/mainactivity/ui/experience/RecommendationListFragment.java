package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.detailactivity.DetailActivity;
import com.hsfa.hearur_android.databinding.FragmentRecommendationListBinding;

public class RecommendationListFragment extends Fragment {

    private FragmentRecommendationListBinding binding;
    private RecommendationViewModel recommendationViewModel;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recommendationViewModel =
                new ViewModelProvider(this).get(RecommendationViewModel.class);

        binding = FragmentRecommendationListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        linearLayout = binding.recommendationList;

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel을 통해 데이터를 가져와 UI에 반영
        recommendationViewModel.getRecommendationList().observe(getViewLifecycleOwner(), items -> {
            linearLayout.removeAllViews(); // 기존 뷰 제거
            for (String item : items) {
                // 커스텀 뷰 추가
                View itemView = getLayoutInflater().inflate(R.layout.experience_item3, linearLayout, false);

                // XML에서 정의된 뷰 요소들 가져오기
                ImageView imageView = itemView.findViewById(R.id.card_image);
                TextView titleText = itemView.findViewById(R.id.card_title);
                TextView subtitleText = itemView.findViewById(R.id.card_subtitle);

                // 데이터 설정
                titleText.setText(item);
                subtitleText.setText("Subtitle for " + item);

                // 임시 이미지 설정
                imageView.setImageResource(R.drawable.image_background5);

                itemView.setOnClickListener(v -> {
                    // 클릭 시 상세 페이지로 이동
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("itemTitle", item);
                    startActivity(intent);
                });

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
