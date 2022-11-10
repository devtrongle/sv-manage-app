package com.sbp.manage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.R;
import com.sbp.manage.databinding.ActivityLoginBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.network.dto.LoginDto;
import com.sbp.manage.network.params.LoginParams;
import com.sbp.manage.utils.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    public static String account;
    public static Boolean isAdmin;

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

        mBtnLogin.setOnClickListener(v -> {
            String username = mEdtUsername.getText().toString();
            String pwd = mEdtPassword.getText().toString();
            if (username.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Utility.showWaitingDialog(LoginActivity.this);
                RetrofitClient.getInstance()
                        .getApiClient()
                        .login(new LoginParams(username, pwd))
                        .enqueue(new Callback<LoginDto>() {
                            @Override
                            public void onResponse(@NonNull Call<LoginDto> call, @NonNull Response<LoginDto> response) {
                                if (response.body() != null && response.body().isSuccess()) {
                                    Log.d(TAG, response.body().toString());
//                                    Pref.getInstance().saveIsAdmin(activity, response.body().isAdmin());
                                    isAdmin = response.body().isAdmin();
                                    account = response.body().getUsername();
                                    getAllContracts((isSuccess, msg) -> {
                                        if (isSuccess) {
                                            getAllEmployments((isSuccess1, msg1) -> {
                                                if (isSuccess1) {
                                                    getAllEmploymentTime((isSuccess2, msg2) -> {
                                                        if (isSuccess2) {
                                                            Utility.dismissWaitingDialog();
                                                            // TODO: di chuyen den dashboard
                                                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                                            finish();
                                                        } else {
                                                            showErrorLogin();
                                                        }
                                                    });
                                                } else {
                                                    showErrorLogin();
                                                }
                                            });
                                        } else {
                                            showErrorLogin();
                                        }
                                    });
                                } else {
                                    showErrorLogin();
                                    Log.e(TAG, "response.body() == null");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<LoginDto> call, @NonNull Throwable t) {
                                showErrorLogin();
                                Log.e(TAG, t.toString());
                            }
                        });
            }
        });
    }

    private void showErrorLogin() {
        Utility.dismissWaitingDialog();
        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!",
                Toast.LENGTH_SHORT).show();
    }

    private void getAllEmployments(@NonNull ICallback callback) {
        RetrofitClient.getInstance()
                .getApiClient()
                .getEmployments()
                .enqueue(
                        new Callback<EmploymentDto>() {
                            @Override
                            public void onResponse(@NonNull Call<EmploymentDto> call,
                                                   @NonNull Response<EmploymentDto> response) {
                                if (response.body() != null && response.body().isSuccess()) {
                                    ManageApplication.sEmploymentList.clear();
                                    ManageApplication.sEmploymentList.addAll(response.body().getEmployment());
                                    callback.onCompleted(true, "");
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentDto> call, @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }

    private void getAllContracts(@NonNull ICallback callback) {
        RetrofitClient.getInstance()
                .getApiClient()
                .getAllContracts()
                .enqueue(
                        new Callback<ContractDto>() {
                            @Override
                            public void onResponse(@NonNull Call<ContractDto> call,
                                                   @NonNull Response<ContractDto> response) {
                                if (response.body() != null && response.body().getSuccess()) {
                                    ManageApplication.sContractList.clear();
                                    ManageApplication.sContractList.addAll(response.body().getContracts());
                                    callback.onCompleted(true, "");
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<ContractDto> call, @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }

    private void getAllEmploymentTime(@NonNull ICallback callback) {
        RetrofitClient.getInstance()
                .getApiClient()
                .getAllEmploymentTime()
                .enqueue(
                        new Callback<EmploymentTimeDto>() {
                            @Override
                            public void onResponse(@NonNull Call<EmploymentTimeDto> call,
                                                   @NonNull Response<EmploymentTimeDto> response) {
                                if (response.body() != null && response.body().getSuccess()) {
                                    ManageApplication.sEmploymentTime.clear();
                                    ManageApplication.sEmploymentTime.addAll(response.body().getList());
                                    callback.onCompleted(true, "");
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentTimeDto> call, @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }

    private interface ICallback {
        void onCompleted(boolean isSuccess, String msg);
    }
}