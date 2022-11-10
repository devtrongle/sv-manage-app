package com.sbp.manage.ui;

import android.app.Application;
import android.content.Context;

import com.sbp.manage.network.dto.ContractDto;
import com.sbp.manage.network.dto.EmploymentDto;
import com.sbp.manage.network.dto.EmploymentTimeDto;

import java.util.ArrayList;


public class ManageApplication extends Application {

    public static Context sApplicationContext;

    public static ArrayList<EmploymentDto.Employment> sEmploymentList;
    public static ArrayList<ContractDto.Contracts> sContractList;
    public static ArrayList<EmploymentTimeDto.ListEmployment> sEmploymentTime;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
        sEmploymentList = new ArrayList<>();
        sContractList = new ArrayList<>();
        sEmploymentTime = new ArrayList<>();
    }
}
