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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
}
