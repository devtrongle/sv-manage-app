package com.sbp.manage.network.params;

import com.google.gson.annotations.SerializedName;

public class DeleteContractParams {
    private String idContract;
    @SerializedName("idEmployment")
    private String employmentId;

    public DeleteContractParams(String idContract, String employmentId) {
        this.idContract = idContract;
        this.employmentId = employmentId;
    }

    public String getIdContract() {
        return idContract;
    }

    public void setIdContract(String idContract) {
        this.idContract = idContract;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
    }
}
