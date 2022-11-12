package com.sbp.manage.network.params;

import com.sbp.manage.network.dto.ContractDto;

public class UpdateContractParams {
    private String idContract;
    private ContractDto.Contracts newContract;

    public String getIdContract() {
        return idContract;
    }

    public void setIdContract(String idContract) {
        this.idContract = idContract;
    }

    public ContractDto.Contracts getNewContract() {
        return newContract;
    }

    public void setNewContract(ContractDto.Contracts newContract) {
        this.newContract = newContract;
    }

    public UpdateContractParams(String idContract,
            ContractDto.Contracts newContract) {
        this.idContract = idContract;
        this.newContract = newContract;
    }
}
