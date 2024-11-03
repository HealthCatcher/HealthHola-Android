package com.hsfa.hearur_android.activity.mainactivity.ui.experience;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.detailactivity.DetailActivity;

import java.util.Arrays;
import java.util.List;

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
        for (String item : getDummyData()) {
            View itemView = getLayoutInflater().inflate(R.layout.experience_item3, linearLayout, false);
            TextView titleText = itemView.findViewById(R.id.card_title);
            titleText.setText(item);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("itemTitle", item);
                startActivity(intent);
            });
            linearLayout.addView(itemView);
        }
    }

    private List<String> getDummyData() {
        return Arrays.asList("Item 1", "Item 2", "Item 3");
    }
}
