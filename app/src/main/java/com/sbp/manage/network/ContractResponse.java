package com.sbp.manage.network;

import com.google.gson.annotations.SerializedName;
import com.sbp.manage.network.dto.ContractDto;

import java.util.List;

public class ContractResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("Contracts")
    private List<ContractDto> contracts;

    public List<ContractDto> getContracts() {
        return contracts;
    }

    public void setContracts(List<ContractDto> contracts) {
        this.contracts = contracts;
    }
}
