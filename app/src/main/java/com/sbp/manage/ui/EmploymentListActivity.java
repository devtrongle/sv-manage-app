package com.sbp.manage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.adapter.EmploymentAdapter;
import com.sbp.manage.databinding.ActivityEmploymentListBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.EmploymentDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmploymentListActivity extends AppCompatActivity {

    private ActivityEmploymentListBinding binding;
    private EmploymentAdapter mEmploymentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmploymentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        mEmploymentAdapter = new EmploymentAdapter();
        mEmploymentAdapter.submitList(ManageApplication.sEmploymentList);
        binding.recyclerViewEmployment.setAdapter(mEmploymentAdapter);

        mEmploymentAdapter.setOnClick(new EmploymentAdapter.IOnClick() {
            @Override
            public void onClick(int position, EmploymentDto.Employment employment) {
                Intent intent = new Intent(EmploymentListActivity.this, IncomeDetailActivity.class);
                intent.putExtra("data", employment);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position, EmploymentDto.Employment employment) {
                Intent intent = new Intent(EmploymentListActivity.this, DetailEmploymentActivity.class);
                intent.putExtra("data", employment);
                startActivity(intent);
            }
        });

        binding.fab.setOnClickListener(view -> {
            startActivity(new Intent(this, DetailEmploymentActivity.class));
        });

        binding.btnSendMailSalary.setOnClickListener(v -> {
            RetrofitClient.getInstance().getApiClient()
                    .sendSalaryMail().enqueue(
                            new Callback<BaseDto>() {
                                @Override
                                public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                    if (response.body() != null) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    "Thông tin bản lương đã được gửi đến mail,vui lòng kiểm tra!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    response.body().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(EmploymentListActivity.this,
                                                "Lỗi server",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseDto> call, Throwable t) {
                                    Log.d("CheckApp", t.toString());
                                    Toast.makeText(EmploymentListActivity.this,
                                            "Lỗi server",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
        });

        binding.btnSendMail2.setOnClickListener(v -> {
            RetrofitClient.getInstance().getApiClient()
                    .sendMail().enqueue(
                            new Callback<BaseDto>() {
                                @Override
                                public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                    if (response.body() != null) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    "Thông tin bảng chấm công đã được gửi đến mail,vui lòng kiểm tra!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    response.body().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(EmploymentListActivity.this,
                                                "Lỗi server",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseDto> call, Throwable t) {
                                    Log.d("CheckApp", t.toString());
                                    Toast.makeText(EmploymentListActivity.this,
                                            "Lỗi server",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                                    mEmploymentAdapter.submitList(ManageApplication.sEmploymentList);
                                    mEmploymentAdapter.notifyDataSetChanged();
                                } else {
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentDto> call, @NonNull Throwable t) {
                            }
                        });

    }
}