package com.hsfa.hearur_android.activity.mainactivity.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        LayoutInflater inflater = LayoutInflater.from(requireContext()); // LayoutInflater 초기화

        for (Post post : posts) {
            // XML 레이아웃 inflate
            View postView = inflater.inflate(R.layout.item_community_post, container, false);

            // 제목, 서브타이틀, 조회수, 추천수 설정
            TextView titleTextView = postView.findViewById(R.id.item_title);
            TextView subtitleTextView = postView.findViewById(R.id.item_subtitle);
            TextView viewsTextView = postView.findViewById(R.id.item_views);
            TextView likesTextView = postView.findViewById(R.id.item_likes);
            ImageView imageView = postView.findViewById(R.id.item_image);

            titleTextView.setText(post.getTitle());
            subtitleTextView.setText(post.getShortContent(30)); // 30글자로 줄여서 표시
            viewsTextView.setText(String.valueOf(post.getViews()));
            likesTextView.setText(String.valueOf(post.getLikes()));
            // 이미지 리소스 설정
            if (post.getImageName() != null) {
                int imageResource = postView.getContext().getResources().getIdentifier(post.getImageName(), "drawable", postView.getContext().getPackageName());
                if (imageResource != 0) {
                    imageView.setImageResource(imageResource);
                } else {
                    // 기본 이미지 설정 (리소스가 없는 경우)
                    imageView.setImageResource(R.drawable.image_example_post); // default_image는 기본 이미지 리소스입니다.
                }
            } else {
                imageView.setImageResource(R.drawable.image_example_post2); // 이미지가 없을 때 사용할 기본 이미지 설정
            }

            // 생성한 View를 컨테이너에 추가
            container.addView(postView);
        }

    }
}
