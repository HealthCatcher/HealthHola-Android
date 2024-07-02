package com.hsfa.hearur_android.mainactivity.ui.community;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.FragmentCommunityBinding;
import com.hsfa.hearur_android.dto.Post;

import java.util.List;

public class CommunityFragment extends Fragment {
    private FragmentCommunityBinding binding;
    private CommunityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel.fetchPostList();
        observeViewModel();

        ImageButton buttonAddPost = binding.buttonAddPost;
        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.communityLayout.removeAllViews();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.community_layout, new PostFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return root;
    }

    private void observeViewModel() {
        viewModel.getPostList().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                displayPosts(posts); // 게시물 목록을 UI에 표시하는 메서드 호출
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void displayPosts(List<Post> posts) {
        LinearLayout container = binding.postList;
        for (Post post : posts) {
            // LinearLayout을 동적으로 생성
            LinearLayout postLayout = new LinearLayout(requireContext());
            postLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            postLayout.setOrientation(LinearLayout.VERTICAL);

            // 제목 TextView 생성 및 설정
            TextView titleTextView = new TextView(requireContext());
            titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            titleTextView.setText(post.getTitle());
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            titleTextView.setTypeface(null, Typeface.BOLD);
            postLayout.addView(titleTextView);

            // 내용 TextView 생성 및 설정
            TextView contentTextView = new TextView(requireContext());
            contentTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            contentTextView.setText(post.getContent());
            contentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            postLayout.addView(contentTextView);

            // 생성한 LinearLayout을 컨테이너에 추가
            container.addView(postLayout);

            // 각 게시물 사이에 구분선 추가 (옵션)
            if (posts.indexOf(post) < posts.size() - 1) {
                View divider = new View(requireContext());
                divider.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        2)); // 구분선의 높이 설정
                divider.setBackgroundColor(Color.LTGRAY);
                container.addView(divider);
            }
        }
    }
}
