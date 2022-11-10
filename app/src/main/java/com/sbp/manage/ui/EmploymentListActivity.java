package com.sbp.manage.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sbp.manage.R;
import com.sbp.manage.adapter.EmploymentAdapter;
import com.sbp.manage.databinding.ActivityEmploymentListBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.EmploymentDto;

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
        binding.recyclerViewEmployment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mEmploymentAdapter.setOnClick((position, employment) -> {
            Intent intent = new Intent(EmploymentListActivity.this, IncomeDetailActivity.class);
            intent.putExtra("data", employment);
            startActivity(intent);
        });
    }
}