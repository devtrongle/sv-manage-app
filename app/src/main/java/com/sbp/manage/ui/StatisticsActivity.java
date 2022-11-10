package com.sbp.manage.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sbp.manage.R;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.EmploymentTimeDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        RetrofitClient.getInstance()
                .getApiClient()
                .getAllEmploymentTime()
                .enqueue(
                new Callback<EmploymentTimeDto>() {
                    @Override
                    public void onResponse(@NonNull Call<EmploymentTimeDto> call,
                            @NonNull Response<EmploymentTimeDto> response) {

                    }

                    @Override
                    public void onFailure(@NonNull Call<EmploymentTimeDto> call, @NonNull Throwable t) {

                    }
                });
    }
}