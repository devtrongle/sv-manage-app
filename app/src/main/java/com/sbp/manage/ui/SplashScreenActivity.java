package com.sbp.manage.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.databinding.ActivitySplashScreenBinding;
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

public class SplashScreenActivity extends AppCompatActivity {

    public static String TAG = SplashScreenActivity.class.getSimpleName();
    private ActivitySplashScreenBinding binding;
    private final Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LoginDto loginDto = Utility.getLogin(mContext);
        if (loginDto != null) {
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();

            RetrofitClient.getInstance()
                    .getApiClient()
                    .login(new LoginParams(username, password))
                    .enqueue(new Callback<LoginDto>() {
                        @Override
                        public void onResponse(@NonNull Call<LoginDto> call,
                                @NonNull Response<LoginDto> response) {
                            if (response.body() != null && response.body().isSuccess()) {
                                Log.d(TAG, response.body().toString());
                                getAllContracts((isSuccess, msg) -> {
                                    if (isSuccess) {
                                        getAllEmployments((isSuccess1, msg1) -> {
                                            if (isSuccess1) {
                                                getAllEmploymentTime((isSuccess2, msg2) -> {
                                                    if (isSuccess2) {
                                                        ManageApplication.sIsAdmin =
                                                                response.body().isAdmin();
                                                        ManageApplication.sAccount =
                                                                response.body().getUsername();
                                                        ManageApplication.sId =
                                                                response.body().getIdEmployment();
                                                        startActivity(new Intent(mContext,
                                                                HomeActivity.class));
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

    }

    private void showErrorLogin() {
        startActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }

    private void getAllEmployments(@NonNull LoginActivity.ICallback callback) {
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
                                    ManageApplication.sEmploymentList.addAll(
                                            response.body().getEmployment());
                                    callback.onCompleted(true, "");
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentDto> call,
                                    @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }

    private void getAllContracts(@NonNull LoginActivity.ICallback callback) {
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
                                    ManageApplication.sContractList.addAll(
                                            response.body().getContracts());
                                    callback.onCompleted(true, "");
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<ContractDto> call,
                                    @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }

    private void getAllEmploymentTime(@NonNull LoginActivity.ICallback callback) {
        RetrofitClient.getInstance()
                .getApiClient()
                .getAllEmploymentTime()
                .enqueue(
                        new Callback<EmploymentTimeDto>() {
                            @Override
                            public void onResponse(@NonNull Call<EmploymentTimeDto> call,
                                    @NonNull Response<EmploymentTimeDto> response) {
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        ManageApplication.sEmploymentTime.clear();
                                        ManageApplication.sEmploymentTime.addAll(
                                                response.body().getList());
                                        callback.onCompleted(true, "");
                                    } else {
                                        callback.onCompleted(false, "No data");
                                    }
                                } else {
                                    callback.onCompleted(false, "No data");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentTimeDto> call,
                                    @NonNull Throwable t) {
                                callback.onCompleted(false, t.getMessage());
                            }
                        });
    }
}