package com.hsfa.hearur_android.loginactivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hsfa.hearur_android.R;
import com.hsfa.hearur_android.databinding.ActivityLoginBinding;
import com.hsfa.hearur_android.dto.UserInfo;
import com.hsfa.hearur_android.network.FetchJWTApiService;
import com.hsfa.hearur_android.network.FetchJWTResponse;
import com.navercorp.nid.NaverIdLoginSDK;

import android.content.Context;

import com.navercorp.nid.oauth.NidOAuthBehavior;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfileMap;
import com.navercorp.nid.profile.data.NidProfileResponse;

import android.view.View;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;
    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String clientId;
    private String clientSecret;
    private String clientName;


    // 백엔드에 jwt토큰 요청
    private void fetchJWT(UserInfo userInfo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.219.100:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FetchJWTApiService fetchJWTApiService = retrofit.create(FetchJWTApiService.class);
        Call<FetchJWTResponse> call = fetchJWTApiService.getJWT(userInfo);
        call.enqueue(new Callback<FetchJWTResponse>() {
            @Override
            public void onResponse(@NonNull Call<FetchJWTResponse> call, @NonNull Response<FetchJWTResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Headers headers = response.headers();
                    String jwt = (String) headers.get("Authorization");
                    if (jwt != null) {
                        sharedPreferences = getSharedPreferences("my_app_pref", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        String saveJWT = jwt.split(" ")[1];
                        editor.putString("jwt", saveJWT);
                        editor.apply();
                        Log.d(TAG, "onResponse: JWT token: " + jwt);
                    } else {
                        Log.e(TAG, "onResponse: JWT token is null");
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FetchJWTResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
// 네이버 프로필 정보 가져오기
//    private void getUserProfile(String accessToken) {
//        Disposable disposable = naverProfileRepository.requestNaverUserProfile(accessToken)
//                .flatMap(this::fetchJWT)
//                .subscribe(
//                        jwtToken -> {
//                            // JWT 토큰을 사용하여 필요한 작업 수행
//                            Log.d("NaverProfile", "JWT Token: " + jwtToken);
//                            sharedPreferences = getSharedPreferences("my_app_pref", MODE_PRIVATE);
//                            editor = sharedPreferences.edit();
//                            editor.putString("jwt", jwtToken);
//                            editor.apply();
//                        },
//                        throwable -> {
//                            // 오류 처리
//                            Log.e("NaverProfile", "Failed to fetch JWT token: " + throwable.getMessage());
//                        }
//                );
//
//        compositeDisposable.add(disposable); // Disposable을 compositeDisposable에 추가
//    }

    // 네이버 로그인 런처
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // 성공
                    updateView();
                    String accessToken = NaverIdLoginSDK.INSTANCE.getAccessToken();
                    UserInfo userInfo = new UserInfo("naver", accessToken);
                    fetchJWT(userInfo);

                } else if (result.getResultCode() == RESULT_CANCELED) {
                    // 실패 or 에러
                    String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                    String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clientId = getResources().getString(R.string.client_id);
        clientSecret = getResources().getString(R.string.client_secret);
        clientName = getResources().getString(R.string.client_name);
        init();
    }

    private void init() {
        context = this;
        // Initialize NAVER id login SDK
        NaverIdLoginSDK.INSTANCE.showDevelopersLog(true);
        NaverIdLoginSDK.INSTANCE.initialize(context, clientId, clientSecret, clientName);
        NaverIdLoginSDK.INSTANCE.setShowMarketLink(true);
        NaverIdLoginSDK.INSTANCE.setShowBottomTab(true);

//        binding.buttonOAuthLoginImg.setOAuthLogin(new OAuthLoginCallback() {
//            @Override
//            public void onSuccess() {
//                updateView();
//            }
//
//            @Override
//            public void onFailure(int httpStatus, @NonNull String message) {
//                String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
//                String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
//                Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(int errorCode, @NonNull String message) {
//                onFailure(errorCode, message);
//            }
//        });

        // 로그인 Launcher
        binding.loginLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.DEFAULT);
                NaverIdLoginSDK.INSTANCE.authenticate(context, launcher);
            }
        });

        // 로그인 Callback
//        binding.loginCallback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.DEFAULT);
//                NaverIdLoginSDK.INSTANCE.authenticate(context, new OAuthLoginCallback() {
//                    @Override
//                    public void onSuccess() {
//                        updateView();
//                    }
//
//                    @Override
//                    public void onFailure(int httpStatus, String message) {
//                        String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
//                        String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
//                        Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(int errorCode, String message) {
//                        onFailure(errorCode, message);
//                    }
//                });
//            }
//        });

        // 로그아웃
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.logout();
                updateView();
            }
        });

        // 연동 끊기
        binding.deleteToken.setOnClickListener(v -> {
            new NidOAuthLogin().callDeleteTokenApi(new OAuthLoginCallback() {
                @Override
                public void onSuccess() {
                    updateView();
                }

                @Override
                public void onFailure(int httpStatus, String message) {
                    String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                    String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    updateView();
                }

                @Override
                public void onError(int errorCode, String message) {
                    onFailure(errorCode, message);
                }
            });

        });

        // 토큰 갱신
        binding.refreshToken.setOnClickListener(v -> {
            new NidOAuthLogin().callRefreshAccessTokenApi(new OAuthLoginCallback() {
                @Override
                public void onSuccess() {
                    updateView();
                }

                @Override
                public void onFailure(int httpStatus, @NonNull String message) {
                    String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                    String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    updateView();
                }

                @Override
                public void onError(int errorCode, @NonNull String message) {
                    onFailure(errorCode, message);
                }
            });
        });

        // Api 호출
        binding.profileApi.setOnClickListener(v -> {
            new NidOAuthLogin().callProfileApi(new NidProfileCallback<NidProfileResponse>() {
                @Override
                public void onSuccess(NidProfileResponse response) {
                    Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    binding.tvApiResult.setText(response.toString());
                }

                @Override
                public void onFailure(int httpStatus, @NonNull String message) {
                    String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                    String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    binding.tvApiResult.setText("");
                }

                @Override
                public void onError(int errorCode, @NonNull String message) {
                    onFailure(errorCode, message);
                }
            });
        });

        // 프로필 Map 호출
        binding.profileMapApi.setOnClickListener(v -> {
            new NidOAuthLogin().getProfileMap(new NidProfileCallback<NidProfileMap>() {
                @Override
                public void onSuccess(NidProfileMap result) {
                    Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
                    binding.tvApiResult.setText(result.toString());
                }

                @Override
                public void onFailure(int httpStatus, String message) {
                    String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                    String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                    Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    binding.tvApiResult.setText("");
                }

                @Override
                public void onError(int errorCode, String message) {
                    onFailure(errorCode, message);
                }
            });
        });
        // 네이버앱 로그인 (Callback)
        binding.loginWithNaverapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.NAVERAPP);
                NaverIdLoginSDK.INSTANCE.authenticate(context, new OAuthLoginCallback() {
                    @Override
                    public void onSuccess() {
                        updateView();
                    }

                    @Override
                    public void onFailure(int httpStatus, @NonNull String message) {
                        String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                        String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                        Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int errorCode, @NonNull String message) {
                        onFailure(errorCode, message);
                    }
                });
            }
        });

        // 커스텀탭 로그인 Callback
        binding.loginWithCustomtabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.CUSTOMTABS);
                NaverIdLoginSDK.INSTANCE.authenticate(context, new OAuthLoginCallback() {
                    @Override
                    public void onSuccess() {
                        updateView();
                    }

                    @Override
                    public void onFailure(int httpStatus, String message) {
                        String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                        String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                        Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int errorCode, String message) {
                        onFailure(errorCode, message);
                    }
                });
            }
        });

        // 웹뷰 로그인
        binding.loginWithWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.WEBVIEW);
                NaverIdLoginSDK.INSTANCE.authenticate(context, new OAuthLoginCallback() {
                    @Override
                    public void onSuccess() {
                        updateView();
                    }

                    @Override
                    public void onFailure(int httpStatus, String message) {
                        String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                        String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                        Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int errorCode, String message) {
                        onFailure(errorCode, message);
                    }
                });
            }
        });

        // 재동의 로그인 Launcher
        binding.reagreeLoginLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.DEFAULT);
                NaverIdLoginSDK.INSTANCE.reagreeAuthenticate(context, launcher);
            }
        });

        // 재동의 로그인 Callback
        binding.reagreeLoginCallback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaverIdLoginSDK.INSTANCE.setBehavior(NidOAuthBehavior.DEFAULT);
                NaverIdLoginSDK.INSTANCE.reagreeAuthenticate(context, new OAuthLoginCallback() {
                    @Override
                    public void onSuccess() {
                        updateView();
                    }

                    @Override
                    public void onFailure(int httpStatus, String message) {
                        String errorCode = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                        String errorDescription = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                        Toast.makeText(context, "errorCode:" + errorCode + ", errorDesc:" + errorDescription, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int errorCode, String message) {
                        onFailure(errorCode, message);
                    }
                });
            }
        });
/*
        // ClientSpinner
        ArrayAdapter<CharSequence> oauthClientSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.client_list,
                android.R.layout.simple_spinner_item
        );
        oauthClientSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.consumerListSpinner.setPrompt("샘플에서 이용할 client 를 선택하세요");
        binding.consumerListSpinner.setAdapter(oauthClientSpinnerAdapter);
        binding.consumerListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(LoginActivity.this,
                        oauthClientSpinnerAdapter.getItem(pos).toString() + "가 선택됨",
                        Toast.LENGTH_SHORT).show();
                if (oauthClientSpinnerAdapter.getItem(pos).equals("네이버아이디로로그인")) {
                    clientId = "fGMii6y0U1sRNvOt5AMw";
                    clientSecret = "KEXtf34wxd";
                    clientName = "hearur";
                } else if (oauthClientSpinnerAdapter.getItem(pos).equals("소셜게임(12G)")) {
                    clientId = "5875kZ1sZ_aL";
                    clientSecret = "509C949A_yi7jOzKU4Pg";
                    clientName = "소셜게임";
                } else if (oauthClientSpinnerAdapter.getItem(pos).equals("ERROR_NO_NAME")) {
                    clientId = "5875kZ1sZ_aL";
                    clientSecret = "509C949A_yi7jOzKU4Pg";
                    clientName = "";
                } else if (oauthClientSpinnerAdapter.getItem(pos).equals("ERROR_CLIENT_ID")) {
                    clientId = "5875kZ1sZ_a";
                    clientSecret = "509C949A_yi7jOzKU4Pg";
                    clientName = "ERROR_CLIENT_ID";
                } else if (oauthClientSpinnerAdapter.getItem(pos).equals("ERROR_CLIENT_SECRET")) {
                    clientId = "jyvqXeaVOVmV";
                    clientSecret = "509C949Ayi7jOzKU4Pg";
                    clientName = "ERROR_CLIENT_SECRET";
                } else {
                    return;
                }
                updateUserData();
                NaverIdLoginSDK.INSTANCE.initialize(context, clientId, clientSecret, clientName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
*/
        // Client 정보 변경
        binding.buttonOAuthInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientId = binding.oauthClientid.getText().toString();
                clientSecret = binding.oauthClientsecret.getText().toString();
                clientName = binding.oauthClientname.getText().toString();

                NaverIdLoginSDK.INSTANCE.initialize(context, clientId, clientSecret, clientName);

                updateUserData();
            }
        });

        updateUserData();
    }

    private void updateView() {
        binding.tvAccessToken.setText(NaverIdLoginSDK.INSTANCE.getAccessToken());
        binding.tvRefreshToken.setText(NaverIdLoginSDK.INSTANCE.getRefreshToken());
        binding.tvExpires.setText(String.valueOf(NaverIdLoginSDK.INSTANCE.getExpiresAt()));
        binding.tvType.setText(NaverIdLoginSDK.INSTANCE.getTokenType());
        binding.tvState.setText(String.valueOf(NaverIdLoginSDK.INSTANCE.getState()));
    }

    private void updateUserData() {
        binding.oauthClientid.setText(clientId);
        binding.oauthClientsecret.setText(clientSecret);
        binding.oauthClientname.setText(clientName);
    }
}