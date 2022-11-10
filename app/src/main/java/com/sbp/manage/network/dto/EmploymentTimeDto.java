package com.sbp.manage.network.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sbp.manage.ui.ManageApplication;
import com.sbp.manage.utils.Utility;

import java.util.List;

public class EmploymentTimeDto {
    @Expose
    @SerializedName("list")
    private List<ListEmployment> list;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;

    public List<ListEmployment> getList() {
        return list;
    }

    public void setList(List<ListEmployment> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ListEmployment {
        @Expose
        @SerializedName("dayAtCompnany")
        private List<DayAtCompnany> dayAtCompany;
        @Expose
        @SerializedName("idEmployment")
        private String idEmployment;

        public List<DayAtCompnany> getDayAtCompany() {
            return dayAtCompany;
        }

        public void setDayAtCompany(List<DayAtCompnany> dayAtCompany) {
            this.dayAtCompany = dayAtCompany;
        }

        public String getIdEmployment() {
            return idEmployment;
        }

        public void setIdEmployment(String idEmployment) {
            this.idEmployment = idEmployment;
        }

        public String getRealSalaryYear() {
            double s = 0;
            for (DayAtCompnany dayAtCompnany : dayAtCompany) {
                s += dayAtCompnany.getRealSalaryDouble(idEmployment);
            }
            return Utility.currencyFormat(s);
        }
    }

    public static class DayAtCompnany {
        @Expose
        @SerializedName("days")
        private List<Integer> days;
        @Expose
        @SerializedName("month")
        private int month;

        public List<Integer> getDays() {
            return days;
        }

        public void setDays(List<Integer> days) {
            this.days = days;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public double getRealSalaryDouble(String idEmployment) {
            com.sbp.manage.network.dto.ContractDto.Contracts contracts = null;
            for (com.sbp.manage.network.dto.ContractDto.Contracts c : ManageApplication.sContractList) {
                if (c.getEmploymentId().equals(idEmployment)){
                    contracts = c;
                    break;
                }
            }
            if (contracts == null) {
                return 0;
            }

            double salaryInDay = Double.parseDouble(contracts.getSalary()) / 30;
            return (salaryInDay * days.size()) + Double.parseDouble(contracts.getBonusProject()) - Double.parseDouble(contracts.getSocialInsurance());
        }

        public String getRealSalary(String idEmployment) {
            com.sbp.manage.network.dto.ContractDto.Contracts contracts = null;
            for (com.sbp.manage.network.dto.ContractDto.Contracts c : ManageApplication.sContractList) {
                if (c.getEmploymentId().equals(idEmployment)){
                    contracts = c;
                    break;
                }
            }
            if (contracts == null) {
                return "0 VND";
            }

            double salaryInDay = Double.parseDouble(contracts.getSalary()) / 30;
            double tmp = (salaryInDay * days.size()) + Double.parseDouble(contracts.getBonusProject()) - Double.parseDouble(contracts.getSocialInsurance());
            return Utility.currencyFormat(tmp);
        }
    }
}
