package com.sbp.manage.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.sbp.manage.adapter.EmploymentAdapter;
import com.sbp.manage.databinding.ActivityEmploymentListBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.params.DeleteContractParams;
import com.sbp.manage.utils.Utility;

import java.util.List;
import java.util.stream.Collectors;

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

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mEmploymentAdapter.submitList(ManageApplication.sEmploymentList);
                    return false;
                }
                List<EmploymentDto.Employment> list = ManageApplication.sEmploymentList.stream()
                        .filter(p -> p.get_id().contains(newText))
                        .collect(Collectors.toList());
                if (list.size() == 0) {
                    list = ManageApplication.sEmploymentList.stream()
                            .filter(p -> p.getName().contains(newText))
                            .collect(Collectors.toList());

                    if (list.size() == 0) {
                        list = ManageApplication.sEmploymentList.stream()
                                .filter(p -> p.getRoleEmployment().contains(newText))
                                .collect(Collectors.toList());
                    }
                }
                mEmploymentAdapter.submitList(list);
                return false;
            }
        });

        mEmploymentAdapter.setOnClick(new EmploymentAdapter.IOnClick() {
            @Override
            public void onClick(int position, EmploymentDto.Employment employment) {
                Intent intent = new Intent(EmploymentListActivity.this, IncomeDetailActivity.class);
                intent.putExtra("data", employment);
                startActivity(intent);
            }

            @Override
            public void onClickEdit(int position, EmploymentDto.Employment employment) {
                Intent intent = new Intent(EmploymentListActivity.this,
                        DetailEmploymentActivity.class);
                intent.putExtra("data", employment);
                startActivity(intent);
            }

            @Override
            public void onClickDeleted(int position, EmploymentDto.Employment employment) {
                new AlertDialog.Builder(EmploymentListActivity.this)
                        .setTitle("Bạn muốn xóa?")
                        .setMessage("Bạn sẽ xóa nhân viên trong danh sách")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> RetrofitClient.getInstance()
                                .getApiClient()
                                .deleteEmployment(new DeleteContractParams("", employment.get_id()))
                                .enqueue(new Callback<BaseDto>() {
                                    @Override
                                    public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Xóa thành công!",
                                                    Toast.LENGTH_SHORT).show();
                                            ManageApplication.sEmploymentList.remove(employment);
                                            mEmploymentAdapter.submitList(ManageApplication.sEmploymentList);
                                            mEmploymentAdapter.notifyDataSetChanged();
                                        } else {
                                            Toast.makeText(getApplicationContext(),
                                                    "Xóa thất bại!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<BaseDto> call, Throwable t) {
                                        Utility.dismissWaitingDialog();
                                        Log.e("CheckApp", t.toString());
                                        Toast.makeText(getApplicationContext(),
                                                "Thêm thất bại!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }))

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_delete)
                        .show();

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
                                public void onResponse(Call<BaseDto> call,
                                                       Response<BaseDto> response) {
                                    if (response.body() != null) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    "Thông tin bản lương đã được gửi đến mail,vui"
                                                            + " lòng kiểm tra!",
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
                                public void onResponse(Call<BaseDto> call,
                                                       Response<BaseDto> response) {
                                    if (response.body() != null) {
                                        if (response.body().getSuccess()) {
                                            Toast.makeText(EmploymentListActivity.this,
                                                    "Thông tin bảng chấm công đã được gửi đến "
                                                            + "mail,vui lòng kiểm tra!",
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
                                    ManageApplication.sEmploymentList.addAll(
                                            response.body().getEmployment());
                                    mEmploymentAdapter.submitList(
                                            ManageApplication.sEmploymentList);
                                    mEmploymentAdapter.notifyDataSetChanged();
                                } else {
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<EmploymentDto> call,
                                                  @NonNull Throwable t) {
                            }
                        });

    }
}