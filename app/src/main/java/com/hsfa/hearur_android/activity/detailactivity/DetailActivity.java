package com.hsfa.hearur_android.activity.detailactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.detailactivity.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 기본 프래그먼트 로드 (예: 신청하기 화면)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_detail_container, new DetailFragment()) // 처음에 신청하기 화면 표시
                    .commit();
        }
    }
}