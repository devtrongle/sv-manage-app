package com.sbp.manage.network.params;

import com.sbp.manage.network.dto.EmploymentDto;

public class UpdateEmploymentParams {
    private String idEmployment;
    private EmploymentDto.Employment newEmployment;

    public UpdateEmploymentParams(String idEmployment, EmploymentDto.Employment newEmployment) {
        this.idEmployment = idEmployment;
        this.newEmployment = newEmployment;
    }

    public String getIdEmployment() {
        return idEmployment;
    }

    public void setIdEmployment(String idEmployment) {
        this.idEmployment = idEmployment;
    }

    public EmploymentDto.Employment getNewEmployment() {
        return newEmployment;
    }

    public void setNewEmployment(EmploymentDto.Employment newEmployment) {
        this.newEmployment = newEmployment;
    }
}
