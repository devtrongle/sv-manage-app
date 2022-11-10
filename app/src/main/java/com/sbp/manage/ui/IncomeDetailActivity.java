package com.sbp.manage.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sbp.manage.R;
import com.sbp.manage.adapter.IncomeAdapter;
import com.sbp.manage.databinding.ActivityIncomeDetailBinding;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.utils.Utility;

import java.util.ArrayList;

public class IncomeDetailActivity extends AppCompatActivity {

    private ActivityIncomeDetailBinding binding;

    private EmploymentDto.Employment mEmployment;

    private ContractDto.Contracts mContracts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });


        mEmployment = (EmploymentDto.Employment) getIntent().getSerializableExtra("data");

        if (mEmployment == null) {
            finish();
            return;
        }

        binding.tvBirthday.setText(mEmployment.getBirthday());
        binding.tvName.setText(mEmployment.getName());
        binding.recyclerViewIncome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        for (ContractDto.Contracts c : ManageApplication.sContractList) {
            if (c.getEmploymentId().equals(mEmployment.get_id())){
                this.mContracts = c;
                break;
            }
        }
        
        if (mContracts == null) {
            Toast.makeText(this, "Không tìm thấy hợp đồng!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        IncomeAdapter incomeAdapter = new IncomeAdapter(mContracts);
        binding.recyclerViewIncome.setAdapter(incomeAdapter);

        ArrayList<EmploymentTimeDto.DayAtCompnany> dayAtCompnany = new ArrayList<>();
        for (EmploymentTimeDto.ListEmployment l : ManageApplication.sEmploymentTime) {
            if (l.getIdEmployment().equals(mEmployment.get_id())){
                dayAtCompnany.addAll(l.getDayAtCompany());
            }
        }
        double totalSalary = Double.parseDouble(mContracts.getSalary()) + Double.parseDouble(mContracts.getBonusProject());
        incomeAdapter.submitList(dayAtCompnany);
        binding.tvTotalIncome.setText(Utility.currencyFormat(totalSalary * dayAtCompnany.size()));
    }
}