package com.sbp.manage.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.sbp.manage.R;
import com.sbp.manage.databinding.ActivityUpdateContractBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateContractActivity extends AppCompatActivity {

    private ActivityUpdateContractBinding binding;

    private ContractDto.Contracts mContracts = null;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateContractBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_update_contract);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        mContracts = (ContractDto.Contracts) getIntent().getSerializableExtra("data");
        if (mContracts == null) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.btnEdit.setVisibility(View.GONE);
            binding.toolbar.setTitle("Thêm hợp đồng");
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.toolbar.setTitle("Cập nhật hợp đồng");
            binding.edtContent.setText(mContracts.getContent());
            binding.edtDescription.setText(mContracts.getDescriptionWork());
            binding.edtProjectBonus.setText(mContracts.getBonusProject());
            binding.edtSalary.setText(mContracts.getSalary());
            binding.edtSocialInsurance.setText(mContracts.getSocialInsurance());
        }


        List<String> name = new ArrayList<>();
        for (EmploymentDto.Employment employment : ManageApplication.sEmploymentList) {
            name.add(employment.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                name);

        binding.spinnerEmployment.setAdapter(adapter);

        if (mContracts != null) {
            for (int i = 0; i < ManageApplication.sEmploymentList.size(); i++) {
                if (mContracts.getEmploymentId()
                        .equals(ManageApplication.sEmploymentList.get(i).get_id())){
                    position = i;
                    break;
                }
            }
            binding.spinnerEmployment.setSelection(position);
            binding.spinnerEmployment.setEnabled(false);
        }

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.showWaitingDialog(UpdateContractActivity.this);
                mContracts.setContent(binding.edtContent.getText().toString());
                mContracts.setDescriptionWork(binding.edtDescription.getText().toString());
                mContracts.setBonusProject(binding.edtProjectBonus.getText().toString());
                mContracts.setSalary(binding.edtSalary.getText().toString());
                mContracts.setSocialInsurance(binding.edtSocialInsurance.getText().toString());

                RetrofitClient.getInstance()
                        .getApiClient()
                        .updateContract(mContracts.get_id(), mContracts)
                        .enqueue(new Callback<BaseDto>() {
                            @Override
                            public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                Utility.dismissWaitingDialog();
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(UpdateContractActivity.this,
                                                "Cập nhật thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        int index = mContracts.indexOf();
                                        if (index != -1) {
                                            ManageApplication.sContractList.set(index,mContracts);
                                        }
                                        finish();
                                    } else {
                                        Toast.makeText(UpdateContractActivity.this,
                                                "Cập nhật thất bại!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(UpdateContractActivity.this,
                                            "Cập nhật thất bại!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseDto> call, Throwable t) {
                                Utility.dismissWaitingDialog();
                                Log.e("CheckApp",t.toString());
                                Toast.makeText(UpdateContractActivity.this,
                                        "Cập nhật thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.showWaitingDialog(UpdateContractActivity.this);
                mContracts = new ContractDto.Contracts();
                mContracts.setEmploymentId(ManageApplication.sEmploymentList.get(position).get_id());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                mContracts.setCreateAt(dateFormat.format(new Date()));
                mContracts.setContent(binding.edtContent.getText().toString());
                mContracts.setDescriptionWork(binding.edtDescription.getText().toString());
                mContracts.setBonusProject(binding.edtProjectBonus.getText().toString());
                mContracts.setSalary(binding.edtSalary.getText().toString());
                mContracts.setSocialInsurance(binding.edtSocialInsurance.getText().toString());

                RetrofitClient.getInstance()
                        .getApiClient()
                        .createContract(mContracts)
                        .enqueue(new Callback<BaseDto>() {
                            @Override
                            public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                Utility.dismissWaitingDialog();
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(UpdateContractActivity.this,
                                                "Thêm thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        ManageApplication.sContractList.add(mContracts);
                                        finish();
                                    } else {
                                        Toast.makeText(UpdateContractActivity.this,
                                                "Thêm thất bại!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(UpdateContractActivity.this,
                                            "Thêm thất bại!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseDto> call, Throwable t) {
                                Utility.dismissWaitingDialog();
                                Log.e("CheckApp",t.toString());
                                Toast.makeText(UpdateContractActivity.this,
                                        "Thêm thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}