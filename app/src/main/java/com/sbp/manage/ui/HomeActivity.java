package com.sbp.manage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sbp.manage.adapter.ContractAdapter;
import com.sbp.manage.databinding.ActivityHomeBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.ContractDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private ContractAdapter contractAdapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        RetrofitClient.getInstance().getApiClient().getAllContracts().enqueue(new Callback<ContractDto>() {
            @Override
            public void onResponse(Call<ContractDto> call, Response<ContractDto> response) {
                if (response.isSuccessful()) {
                    ManageApplication.sContractList.addAll(response.body().getContracts());
                    contractAdapter.setData((ArrayList<ContractDto.Contracts>) response.body().getContracts());
                }
            }

            @Override
            public void onFailure(Call<ContractDto> call, Throwable t) {

            }
        });
    }

    private void initView() {
        contractAdapter = new ContractAdapter();
        binding.rc.setLayoutManager(new LinearLayoutManager(this));
        binding.rc.setAdapter(contractAdapter);

        binding.tvListEmployment.setOnClickListener(view -> {
            startActivity(new Intent(this, EmploymentListActivity.class));
        });
        contractAdapter.setOnClick((position, data) -> {
            Intent intent = new Intent(this, DetailContractActiviy.class);
            intent.putExtra("data", data);
            startActivity(intent);
        });

        binding.contentTitle.setText("Hi," + LoginActivity.account);
        if (!LoginActivity.isAdmin) {
            binding.tvListEmployment.setVisibility(View.GONE);
        }

        binding.fab.setOnClickListener(view -> {
            startActivity(new Intent(this, DetailContractActiviy.class));
        });

    }

}