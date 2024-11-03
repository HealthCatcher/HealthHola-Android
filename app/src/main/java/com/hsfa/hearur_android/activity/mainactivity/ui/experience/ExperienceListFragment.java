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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.detailactivity.DetailActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExperienceListFragment extends Fragment {

    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience_list, container, false);
        linearLayout = view.findViewById(R.id.experience_list);
        populateExperienceList();
        return view;
    }

    private void populateExperienceList() {
        // 데이터를 동적으로 추가
        for (Map<String, String> item : getDummyData()) {
            View itemView = getLayoutInflater().inflate(R.layout.experience_item3, linearLayout, false);

            TextView titleText = itemView.findViewById(R.id.card_title);
            TextView subtitleText = itemView.findViewById(R.id.card_subtitle);
            TextView periodText = itemView.findViewById(R.id.card_period);
            ImageView imageView = itemView.findViewById(R.id.card_product);
            ImageView imagebanner = itemView.findViewById(R.id.card_image);

            titleText.setText(item.get("title"));
            subtitleText.setText(item.get("subtitle"));
            periodText.setText("~" + item.get("period"));
            if(item.containsKey("image")) {
                int imageId = getResources().getIdentifier(item.get("image"), "drawable", getActivity().getPackageName());
                imageView.setImageResource(imageId);
            }
            if(item.containsKey("banner")) {
                int imageId = getResources().getIdentifier(item.get("banner"), "drawable", getActivity().getPackageName());
                imagebanner.setImageResource(imageId);
            }

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("itemTitle", item.get("title"));
                startActivity(intent);
            });

            linearLayout.addView(itemView);
        }
    }


    private List<Map<String, String>> getDummyData() {
        List<Map<String, String>> data = new ArrayList<>();

        Map<String, String> item1 = new HashMap<>();
        item1.put("title", "비타민 C 보충제");
        item1.put("subtitle", "면역력 강화 및 피로 회복");
        item1.put("period", "2024-11-05");
        item1.put("image", "vitaminc");
        data.add(item1);

        Map<String, String> item2 = new HashMap<>();
        item2.put("title", "오메가-3 지방산");
        item2.put("subtitle", "심혈관 건강과 뇌 기능 개선");
        item2.put("period", "2024-11-10");
        item2.put("image", "omega3");
        data.add(item2);

        Map<String, String> item3 = new HashMap<>();
        item3.put("title", "프로바이오틱스");
        item3.put("subtitle", "장 건강 및 소화 개선에 도움");
        item3.put("period", "2024-11-15");
        data.add(item3);

        Map<String, String> item4 = new HashMap<>();
        item4.put("title", "칼슘 보충제");
        item4.put("subtitle", "뼈 건강 및 골다공증 예방에 필수");
        item4.put("period", "2024-11-20");
        data.add(item4);

        Map<String, String> item5 = new HashMap<>();
        item5.put("title", "단백질 파우더");
        item5.put("subtitle", "운동 후 근육 회복에 효과적");
        item5.put("period", "2024-11-25");
        data.add(item5);

        return data;
    }


}
