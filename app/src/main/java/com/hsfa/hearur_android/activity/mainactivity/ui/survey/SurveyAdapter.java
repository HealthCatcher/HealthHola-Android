package com.hsfa.hearur_android.activity.mainactivity.ui.survey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hsfa.hearur_android.R;

import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.SurveyViewHolder> {
    private List<String> surveyList;

    public SurveyAdapter(List<String> surveyList) {
        this.surveyList = surveyList;
    }

    @NonNull
    @Override
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // survey_item.xml을 인플레이트하여 ViewHolder에 전달
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_item, parent, false);
        return new SurveyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyViewHolder holder, int position) {
        // 설문조사 항목을 설정
        String surveyItem = surveyList.get(position);
        holder.surveyTitle.setText(surveyItem);

        // 클릭 리스너 설정 (추가 기능 필요 시)
        holder.itemView.setOnClickListener(v -> {
            // 클릭 시 특정 설문조사로 이동하는 로직 추가
            // 예시: Toast나 새로운 페이지로 이동
        });
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }

    public static class SurveyViewHolder extends RecyclerView.ViewHolder {
        TextView surveyTitle;

        public SurveyViewHolder(@NonNull View itemView) {
            super(itemView);
            surveyTitle = itemView.findViewById(R.id.survey_title);
        }
    }
}
