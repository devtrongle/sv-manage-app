package com.sbp.manage.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.databinding.ActivityDetailContractBinding;

public class DetailContractActiviy extends AppCompatActivity {

    private ActivityDetailContractBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailContractBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}