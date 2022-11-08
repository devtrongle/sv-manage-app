package com.sbp.manage.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sbp.manage.R;
import com.sbp.manage.databinding.ActivityLoginBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.LoginDto;
import com.sbp.manage.network.params.LoginParams;
import com.sbp.manage.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mEdtUsername = findViewById(R.id.edtUsername);
        mEdtPassword = findViewById(R.id.edtPassword);
        mBtnLogin = findViewById(R.id.btnLogin);

        mBtnLogin.setOnClickListener( v -> {
            String username = mEdtUsername.getText().toString();
            String pwd = mEdtPassword.getText().toString();
            if (username.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                RetrofitClient.getInstance()
                        .mApiClient
                        .login(new LoginParams(username, pwd))
                        .enqueue(new Callback<LoginDto>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginDto> call, @NonNull Response<LoginDto> response) {
                        if (response.body() != null) {
                            Log.d(TAG, response.body().toString());
                        } else {
                            Log.e(TAG, "response.body() == null");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginDto> call, @NonNull Throwable t) {
                        Log.e(TAG, t.toString());
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}