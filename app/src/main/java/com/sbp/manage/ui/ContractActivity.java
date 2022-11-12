package com.sbp.manage.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sbp.manage.adapter.ContractAdapter;
import com.sbp.manage.adapter.EmploymentAdapter;
import com.sbp.manage.databinding.ActivityContractBinding;

public class ContractActivity extends AppCompatActivity {

    private ActivityContractBinding binding;

    private ContractAdapter mContractAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContractBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        mContractAdapter = new ContractAdapter(this);
        binding.recyclerViewContract.setAdapter(mContractAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContractAdapter.submitList(ManageApplication.sContractList);
    }
}