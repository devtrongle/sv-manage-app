package com.sbp.manage.network;

import com.sbp.manage.network.dto.BaseDto;
import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.CreateEmploymentDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;
import com.sbp.manage.network.dto.LoginDto;
import com.sbp.manage.network.params.CreateContractParams;
import com.sbp.manage.network.params.CreateEmploymentParams;
import com.sbp.manage.network.params.DeleteContractParams;
import com.sbp.manage.network.params.LoginParams;
import com.sbp.manage.network.params.UpdateContractParams;
import com.sbp.manage.network.params.UpdateEmploymentParams;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("/user/login")
    Call<LoginDto> login(@Body LoginParams params);

    @POST("/employment/employments")
    Call<EmploymentDto> getEmployments();

    @POST("/mail")
    Call<BaseDto> sendMail();

    @POST("/mail/salary")
    Call<BaseDto> sendSalaryMail();

    @POST("/contract/contracts")
    Call<ContractDto> getAllContracts();

    @POST("/contract")
    Call<BaseDto> createContract(@Body CreateContractParams params);

    @HTTP(method = "DELETE", path = "/contract/contracts", hasBody = true)
    Call<BaseDto> deleteContract(@Body DeleteContractParams params);

    @HTTP(method = "PUT", path = "/contract/contracts", hasBody = true)
    Call<BaseDto> updateContract(@Body UpdateContractParams params);

    @POST("/employment")
    Call<CreateEmploymentDto> createEmployment(@Body CreateEmploymentParams params);

    @POST("/employment/time")
    Call<EmploymentTimeDto> getAllEmploymentTime();

    @HTTP(method = "PUT", path = "/employment/employments", hasBody = true)
    Call<BaseDto> updateEmployment(@Body UpdateEmploymentParams params);

    @HTTP(method = "DELETE", path = "/employment/employments", hasBody = true)
    Call<BaseDto> deleteEmployment(@Body DeleteContractParams params);
}
