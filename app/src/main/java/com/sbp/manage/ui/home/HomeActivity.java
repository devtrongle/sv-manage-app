package com.sbp.manage.ui.home;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sbp.manage.R;
import com.sbp.manage.network.ContractResponse;
import com.sbp.manage.network.RetrofitClient;
import com.sbp.manage.network.dto.ContractDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rc;
    private ContractAdapter contractAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        RetrofitClient.getInstance()
                .mApiClient
                .getAllContracts()
                .enqueue(new Callback<ContractResponse>() {
                    @Override
                    public void onResponse(Call<ContractResponse> call, Response<ContractResponse> response) {
                        if (response.isSuccessful()) {
                            contractAdapter.setData((ArrayList<ContractDto>) response.body().getContracts());
                        }
                    }

                    @Override
                    public void onFailure(Call<ContractResponse> call, Throwable t) {

                    }
                });
    }

    private void initView() {
        rc = findViewById(R.id.rc);
        contractAdapter = new ContractAdapter(getApplicationContext());
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(contractAdapter);
    }

    private void handleResults(ContractResponse o) {
        Log.d("TAG", "handleResults: " + o);
    }
}