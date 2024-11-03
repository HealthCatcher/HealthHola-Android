package com.hsfa.hearur_android.activity.mainactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.mainactivity.ui.startpage.StartPageFragment;
import com.hsfa.hearur_android.databinding.ActivityMainBinding;
import com.hsfa.hearur_android.activity.mainactivity.ui.experience.ExperienceFragment;
import com.hsfa.hearur_android.activity.mainactivity.ui.community.CommunityFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayShowCustomEnabled(true);
            ab.setDisplayShowTitleEnabled(false);

            // 홈 버튼에 클릭 이벤트 설정
            ImageView homeButton = findViewById(R.id.home_button);
            TextView homeTitle = findViewById(R.id.home_text);
            View.OnClickListener homeClickListener = v -> navigateToStartPage();

            homeButton.setOnClickListener(homeClickListener);
            homeTitle.setOnClickListener(homeClickListener);
        }

        // SharedPreferences 초기화
        sharedPreferences = getSharedPreferences("my_app_pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // 드로어 메뉴 아이템 선택 리스너
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        });

        // 커스텀 네비게이션 바의 탭 설정
        setupCustomNavigationBar();

        if (savedInstanceState == null) {
            loadFragment(new StartPageFragment());
        }

        // 뒤로가기 버튼 처리
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    finish();
                }
            }
        });
    }

    private void navigateToStartPage() {
        // 초기 페이지로 이동
        loadFragment(new ExperienceFragment());
    }

    private void setupCustomNavigationBar() {
        TextView tabLeft = findViewById(R.id.tab_left);
        TextView tabRight = findViewById(R.id.tab_right);

        tabLeft.setOnClickListener(v -> loadFragment(new ExperienceFragment()));
        tabRight.setOnClickListener(v -> loadFragment(new CommunityFragment()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            drawerLayout.openDrawer(GravityCompat.END);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
