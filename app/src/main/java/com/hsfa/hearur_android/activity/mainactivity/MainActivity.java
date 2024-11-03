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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.ActivityMainBinding;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Toolbar를 ActionBar로 설정
        // MainActivity.java
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

        sharedPreferences = getSharedPreferences("my_app_pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // 로그인 로직 비활성화!!!!!!!!!!!!!!!!!!!!!!!!!
        // 사용 예시: 로그인 상태 가져오기
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//        if(!isLoggedIn) {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_healthinfo, R.id.navigation_diagnosis, R.id.navigation_experience, R.id.navigation_community, R.id.navigation_settings
        )
                .build();
        */
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_startpage, R.id.navigation_experience, R.id.navigation_community, R.id.navigation_survey
        )
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        // NavigationView 아이템 선택 리스너
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

//            if (itemId == R.id.action_profile) {
//                Toast.makeText(this, "프로필 선택됨", Toast.LENGTH_SHORT).show();
//            } else if (itemId == R.id.action_points) {
//                Toast.makeText(this, "포인트 화면 선택됨", Toast.LENGTH_SHORT).show();
//            }
            // 추가적인 메뉴 항목 처리
            drawerLayout.closeDrawer(GravityCompat.END); // 드로어 닫기
            return true;
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 드로어가 열려 있을 때 닫음
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END);
                } else {
                    // 드로어가 닫혀 있을 때 기본 뒤로 가기 동작 수행
                    setEnabled(false);  // 콜백 비활성화
                    finish();
                }
            }
        });
    }

    private void navigateToStartPage() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_startpage);
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
            // 설정 버튼이 눌렸을 때 동작할 코드 작성
            drawerLayout.openDrawer(GravityCompat.END); // 우측 드로어 열기
            return true;
        }

        if (id == R.id.action_notice) {
            // 공지사항 버튼이 눌렸을 때 동작할 코드 작성
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
    }
}