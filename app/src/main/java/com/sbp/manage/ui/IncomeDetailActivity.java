package com.sbp.manage.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
        binding.recyclerViewIncome.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        for (ContractDto.Contracts c : ManageApplication.sContractList) {
            if (c.getEmploymentId().equals(mEmployment.get_id())) {
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
            if (l.getIdEmployment().equals(mEmployment.get_id())) {
                dayAtCompnany.addAll(l.getDayAtCompany());
            }
        }
        double totalSalary = Double.parseDouble(mContracts.getSalary()) + Double.parseDouble(
                mContracts.getBonusProject());
        incomeAdapter.submitList(dayAtCompnany);
        binding.tvTotalIncome.setText(Utility.currencyFormat(totalSalary * dayAtCompnany.size()));

        binding.chart.setDrawBarShadow(false);
        binding.chart.setDrawValueAboveBar(true);

        binding.chart.getDescription().setEnabled(false);
        binding.chart.setMaxVisibleValueCount(60);
        binding.chart.setPinchZoom(false);

        binding.chart.setDrawGridBackground(false);

        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(15);

        YAxis leftAxis = binding.chart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = binding.chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f);

        binding.chart.getLegend().setEnabled(false);

        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            boolean flag = false;
            float month = (float) (i + 1);
            for (EmploymentTimeDto.DayAtCompnany d : dayAtCompnany) {
                if (month == d.getMonth()) {
                    flag = true;
                    values.add(new BarEntry(month,
                            (float) d.getRealSalaryDouble(mEmployment.get_id())));
                    break;
                }
            }

            if (!flag) {
                values.add(new BarEntry(month, 0));
            }
        }

        BarDataSet set1;
        if (binding.chart.getData() != null &&
                binding.chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) binding.chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            binding.chart.getData().notifyDataChanged();
            binding.chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "");
            set1.setDrawIcons(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            binding.chart.setData(data);
        }
        binding.chart.invalidate();
    }
}