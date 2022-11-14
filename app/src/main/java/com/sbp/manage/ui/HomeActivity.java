package com.sbp.manage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sbp.manage.adapter.ContractAdapter;
import com.sbp.manage.databinding.ActivityHomeBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.ContractDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()) {
                    contractAdapter.setData(ManageApplication.sContractList);
                    return false;
                }
                List<ContractDto.Contracts> list = ManageApplication.sContractList.stream()
                        .filter(p -> p.get_id().contains(query))
                        .collect(Collectors.toList());
                if (list.size() == 0) {
                    list = ManageApplication.sContractList.stream()
                            .filter(p -> p.getContent().contains(query))
                            .collect(Collectors.toList());

                    if (list.size() == 0) {
                        list = ManageApplication.sContractList.stream()
                                .filter(p -> p.getDescriptionWork().contains(query))
                                .collect(Collectors.toList());
                    }
                }
                contractAdapter.setData((ArrayList<ContractDto.Contracts>) list);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitClient.getInstance().getApiClient().getAllContracts().enqueue(new Callback<ContractDto>() {
            @Override
            public void onResponse(Call<ContractDto> call, Response<ContractDto> response) {
                if (response.isSuccessful()) {
                    ManageApplication.sContractList.clear();
                    ManageApplication.sContractList.addAll(response.body().getContracts());
                    contractAdapter.setData((ArrayList<ContractDto.Contracts>) response.body().getContracts());
                }
            }

            @Override
            public void onFailure(Call<ContractDto> call, Throwable t) {

            }
        });
    }
}