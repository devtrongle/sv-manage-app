package com.sbp.manage.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.R;
import com.sbp.manage.databinding.ActivityDetailContractBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.params.CreateContractParams;
import com.sbp.manage.network.params.DeleteContractParams;
import com.sbp.manage.network.params.UpdateContractParams;
import com.sbp.manage.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailContractActiviy extends AppCompatActivity {

    private ActivityDetailContractBinding binding;
    private ContractDto.Contracts mContracts = null;
    private final Context mContext = this;
    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailContractBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContracts = (ContractDto.Contracts) getIntent().getSerializableExtra("data");
        if (mContracts == null) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.btnEdit.setVisibility(View.GONE);
            binding.btnDelete.setVisibility(View.GONE);
            binding.layoutId.setVisibility(View.GONE);
            binding.toolbar.setTitle("Thêm hợp đồng");
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.btnDelete.setVisibility(View.VISIBLE);
            binding.toolbar.setTitle("Cập nhật hợp đồng");
            binding.edtContent.setText(mContracts.getContent());
            binding.edtDesWork.setText(mContracts.getDescriptionWork());
            binding.edtBonus.setText(mContracts.getBonusProject());
            binding.edtSalary.setText(mContracts.getSalary());
            binding.edtBHXH.setText(mContracts.getSocialInsurance());
            binding.id.setText(mContracts.get_id());
            binding.layoutId.setVisibility(View.VISIBLE);
        }


        List<String> name = new ArrayList<>();
        for (EmploymentDto.Employment employment : ManageApplication.sEmploymentList) {
            name.add(employment.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item,
                name);

        binding.spinnerEmployment.setAdapter(adapter);
        if (mContracts != null) {
            for (int i = 0; i < ManageApplication.sEmploymentList.size(); i++) {
                if (mContracts.getEmploymentId()
                        .equals(ManageApplication.sEmploymentList.get(i).get_id())) {
                    mPosition = i;
                    break;
                }
            }
            binding.spinnerEmployment.setSelection(mPosition);
            binding.spinnerEmployment.setEnabled(false);
        }

        binding.spinnerEmployment.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                            long id) {
                        mPosition = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient.getInstance()
                        .getApiClient()
                        .deleteContract(new DeleteContractParams(mContracts.get_id(),
                                mContracts.getEmploymentId()))
                        .enqueue(new Callback<BaseDto>() {
                            @Override
                            public void onResponse(
                                    Call<BaseDto> call, Response<BaseDto> response) {
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(mContext,
                                                "Xoá hợp đồng thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        if (mContracts.indexOf() != -1) {
                                            ManageApplication.sContractList.remove(
                                                    mContracts.indexOf());
                                        }
                                        if (mContracts.indexInEmployment() != -1) {
                                            ManageApplication.sEmploymentList.remove(
                                                    mContracts.indexInEmployment());
                                        }
                                        finish();
                                    } else {
                                        Toast.makeText(mContext,
                                                "Xoá hợp đồng không thành công, thử "
                                                        + "lại sau!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext,
                                            "Xoá hợp đồng không thành công, thử lại "
                                                    + "sau!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseDto> call, Throwable t) {
                                Log.e("CheckApp", t.toString());
                                Toast.makeText(mContext,
                                        "Xoá hợp đồng không thành công, thử lại sau!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Utility.showWaitingDialog(mContext);
                mContracts.setContent(binding.edtContent.getText().toString());
                mContracts.setDescriptionWork(binding.edtDesWork.getText().toString());
                mContracts.setBonusProject(binding.edtBonus.getText().toString());
                mContracts.setSalary(binding.edtSalary.getText().toString());
                mContracts.setSocialInsurance(binding.edtBHXH.getText().toString());

                RetrofitClient.getInstance()
                        .getApiClient()
                        .updateContract(new UpdateContractParams(mContracts.get_id(), mContracts))
                        .enqueue(new Callback<BaseDto>() {
                            @Override
                            public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                Utility.dismissWaitingDialog();
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(mContext,
                                                "Cập nhật thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        ManageApplication.sContractList.set(mContracts.indexOf(),
                                                mContracts);
                                        finish();
                                    } else {
                                        Toast.makeText(mContext,
                                                "Cập nhật thất bại!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext,
                                            "Cập nhật thất bại!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseDto> call, Throwable t) {
                                Utility.dismissWaitingDialog();
                                Log.e("CheckApp", t.toString());
                                Toast.makeText(mContext,
                                        "Cập nhật thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Utility.showWaitingDialog(mContext);
                CreateContractParams createContractParams = new CreateContractParams();
                createContractParams.setEmploymentId(
                        ManageApplication.sEmploymentList.get(mPosition).get_id());
                createContractParams.setContent(binding.edtContent.getText().toString());
                createContractParams.setDescriptionWork(binding.edtDesWork.getText().toString());
                createContractParams.setBonusProject(binding.edtBonus.getText().toString());
                createContractParams.setSalary(binding.edtSalary.getText().toString());
                createContractParams.setSocialInsurance(binding.edtBHXH.getText().toString());
                RetrofitClient.getInstance()
                        .getApiClient()
                        .createContract(createContractParams)
                        .enqueue(new Callback<BaseDto>() {
                            @Override
                            public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                                Utility.dismissWaitingDialog();
                                if (response.body() != null) {
                                    if (response.body().getSuccess()) {
                                        Toast.makeText(mContext,
                                                "Thêm thành công!",
                                                Toast.LENGTH_SHORT).show();
                                        ManageApplication.sContractList.add(mContracts);
                                        finish();
                                    } else {
                                        Toast.makeText(mContext,
                                                "Thêm thất bại!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(mContext,
                                            "Thêm thất bại!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseDto> call, Throwable t) {
                                Utility.dismissWaitingDialog();
                                Log.e("CheckApp", t.toString());
                                Toast.makeText(mContext,
                                        "Thêm thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}