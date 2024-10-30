package com.hsfa.hearur_android.activity.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.activity.settingsactivity.SettingsActivity;
import com.hsfa.hearur_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                R.id.navigation_experience, R.id.navigation_community, R.id.navigation_survey
        )
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


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
            Intent intent = new Intent(this, SettingsActivity.class); // 예시로 설정 화면으로 이동
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}