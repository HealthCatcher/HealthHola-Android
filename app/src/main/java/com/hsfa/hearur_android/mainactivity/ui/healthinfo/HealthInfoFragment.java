package com.hsfa.hearur_android.mainactivity.ui.healthinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentHealthinfoBinding;

public class HealthInfoFragment extends Fragment {

    private FragmentHealthinfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HealthInfoViewModel healthInfoViewModel =
                new ViewModelProvider(this).get(HealthInfoViewModel.class);

        binding = FragmentHealthinfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ScrollView scrollView = binding.healthInfoScrollView;
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(requireContext());
        textView.setTextSize(20);
        textView.setText("(예시)심혈관 질환");
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ImageView imageView = new ImageView(requireContext());
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView textView2 = new TextView(requireContext());
        textView2.setTextSize(15);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView2.setText("통매가 츠온다베어 미리속은 곡졔새 달다하므다 와어는 리부지다. 골로에 이판실 자믄 이근낼듬이다 돌먄자다. 이오 기깅소오, 젼어사하 젼큽애대 입다를 에먼 앙삼인스 힘리아당을. 내켤애앙은 므디와키역업다 탕어를 언우로다 격그모기발, 아버다. 좡랄도 으바공 키소미니께서 웄히수머어야 몽뎌멧마기다, 기아기지만 누슁의 서능군다 묘바넹얼닐 으빌의. 리졸놀디의 저롭 내홑쏘");
        linearLayout.addView(textView);
        linearLayout.addView(imageView);
        linearLayout.addView(textView2);
        scrollView.addView(linearLayout);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}