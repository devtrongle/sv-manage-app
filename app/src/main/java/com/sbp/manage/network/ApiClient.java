package com.sbp.manage.network;

import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.CreateEmploymentDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.network.dto.LoginDto;
import com.sbp.manage.network.params.CreateContractParams;
import com.sbp.manage.network.params.CreateEmploymentParams;
import com.sbp.manage.network.params.LoginParams;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @POST("/employment/employments")
    Call<EmploymentDto> getEmployments();

    @POST("/mail")
    Call<BaseDto> sendMail();

    @POST("/contract/contracts")
    Call<ContractDto> getAllContracts();

    @POST("/contract")
    Call<BaseDto> createContract(@Body CreateContractParams params);

    @POST("/employment")
    Call<CreateEmploymentDto> createEmployment(@Body CreateEmploymentParams params);

    @POST("/employment/time")
    Call<EmploymentTimeDto> getAllEmploymentTime();
}
