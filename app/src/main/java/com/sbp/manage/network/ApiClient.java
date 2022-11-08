package com.sbp.manage.network;

import com.sbp.manage.network.dto.LoginDto;
import com.sbp.manage.network.params.LoginParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {

    @Headers("Content-Type: application/json")
    @POST("/user/login")
    Call<LoginDto> login(@Body LoginParams params);

    @Headers("Content-Type: application/json")
    @POST("/contract/contracts")
    Call<ContractResponse> getAllContracts();
}
