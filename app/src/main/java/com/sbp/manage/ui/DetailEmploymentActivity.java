package com.sbp.manage.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sbp.manage.R;
import com.sbp.manage.databinding.ActivityDetailEmploymentBinding;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.CreateEmploymentDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.params.CreateEmploymentParams;
import com.sbp.manage.network.params.DeleteContractParams;
import com.sbp.manage.network.params.UpdateEmploymentParams;
import com.sbp.manage.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEmploymentActivity extends AppCompatActivity {

    private ActivityDetailEmploymentBinding binding;
    private EmploymentDto.Employment mEmployment = null;
    private final Context mContext = this;
    private final int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailEmploymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        mEmployment = (EmploymentDto.Employment) getIntent().getSerializableExtra("data");
        if (mEmployment == null) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.btnEdit.setVisibility(View.GONE);
            binding.layoutId.setVisibility(View.GONE);
            binding.toolbar.setTitle("Thêm nhân viên");
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.btnEdit.setVisibility(View.VISIBLE);
            binding.toolbar.setTitle("Cập nhật nhân viên");
            binding.edtName.setText(mEmployment.getName());
            binding.edtDate.setText(mEmployment.getBirthday());
            binding.spinnerRole.setSelection(ManageApplication.sEmploymentList.indexOf(mEmployment));
            binding.edtPhone.setText(mEmployment.getPhone());
            binding.edtEmail.setText(mEmployment.getEmail());
            binding.id.setText(mEmployment.get_id());
            binding.layoutId.setVisibility(View.VISIBLE);
        }

        List<String> sex = new ArrayList<>();
        sex.add("Nam");
        sex.add("Nữ");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_item,
                sex);
        binding.spinnerSex.setAdapter(adapter);

        binding.spinnerSex.setSelection(mPosition);
        binding.spinnerSex.setEnabled(true);

        List<String> role = new ArrayList<>();
//        Giám đốc, Phó Giám đốc, Quản lý, Nhân viên

        role.add("Giám đốc kinh doanh");
        role.add("Giám đốc công nghệ");
        role.add("Giám đốc nhân sự");
        role.add("Phó giám đốc kinh doanh");
        role.add("Phó giám đốc công nghệ");
        role.add("Phó giám đốc nhân sự");
        role.add("Quản lý kinh doanh");
        role.add("Quản lý công nghệ");
        role.add("Quản lý nhân sự");
        role.add("Nhân viên kinh doanh");
        role.add("Nhân viên công nghệ");
        role.add("Nhân viên tuyển dụng");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                R.layout.spinner_item,
                role);
        binding.spinnerRole.setAdapter(adapter2);

        binding.btnAdd.setOnClickListener(V -> {
            CreateEmploymentParams params = new CreateEmploymentParams();
            params.setName(binding.edtName.getText().toString());
            params.setBirthday(binding.edtDate.getText().toString());
            params.setPhone(binding.edtPhone.getText().toString());
            params.setRoleEmployment(binding.spinnerRole.getSelectedItem().toString());
            params.setEmail(binding.edtEmail.getText().toString());
            params.setJoinDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            String text = binding.spinnerSex.getSelectedItem().toString();
            Boolean isMale;
            isMale = text.equals("Name");
            params.setSex(isMale);

            RetrofitClient.getInstance()
                    .getApiClient()
                    .createEmployment(params)
                    .enqueue(new Callback<CreateEmploymentDto>() {
                        @Override
                        public void onResponse(Call<CreateEmploymentDto> call,
                                Response<CreateEmploymentDto> response) {
                            if (response.body().getSuccess()) {
                                Toast.makeText(mContext,
                                        "Thêm thành công!",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(mContext,
                                        "Thêm thất bại!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateEmploymentDto> call, Throwable t) {
                            Utility.dismissWaitingDialog();
                            Log.e("CheckApp", t.toString());
                            Toast.makeText(mContext,
                                    "Thêm thất bại!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        binding.btnEdit.setOnClickListener(v -> {
            EmploymentDto.Employment params = new EmploymentDto.Employment();
            params.setName(binding.edtName.getText().toString());
            params.setBirthday(binding.edtDate.getText().toString());
            params.setPhone(binding.edtPhone.getText().toString());
            params.setRoleEmployment(binding.spinnerRole.getSelectedItem().toString());
            params.setEmail(binding.edtEmail.getText().toString());
            params.setJoinDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            String text = binding.spinnerSex.getSelectedItem().toString();
            Boolean isMale;
            isMale = text.equals("Name");
            params.setSex(isMale);
            RetrofitClient.getInstance()
                    .getApiClient()
                    .updateEmployment(new UpdateEmploymentParams(mEmployment.get_id(), params))
                    .enqueue(new Callback<BaseDto>() {
                        @Override
                        public void onResponse(Call<BaseDto> call, Response<BaseDto> response) {
                            if (response.body().getSuccess()) {
                                Toast.makeText(mContext,
                                        "Sửa thành công!",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(mContext,
                                        "Sửa thất bại!",
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
        });
    }
}