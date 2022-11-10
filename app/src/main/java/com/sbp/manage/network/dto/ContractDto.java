package com.sbp.manage.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ContractDto {
    @Expose
    @SerializedName("Contracts")
    private List<Contracts> Contracts;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;

    public List<Contracts> getContracts() {
        return Contracts;
    }

    public void setContracts(List<Contracts> Contracts) {
        this.Contracts = Contracts;
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

    @NonNull
    @Override
    public String toString() {
        return "ContractDto{" +
                "Contracts=" + Contracts +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }

    public static class Contracts implements Serializable {
        @Expose
        @SerializedName("bonusProject")
        private String bonusProject;
        @Expose
        @SerializedName("socialInsurance")
        private String socialInsurance;
        @Expose
        @SerializedName("salary")
        private String salary;
        @Expose
        @SerializedName("descriptionWork")
        private String descriptionWork;
        @Expose
        @SerializedName("content")
        private String content;
        @Expose
        @SerializedName("createAt")
        private String createAt;
        @Expose
        @SerializedName("employmentId")
        private String employmentId;
        @Expose
        @SerializedName("_id")
        private String _id;

        public String getBonusProject() {
            if (bonusProject == null) {
                return "0";
            } else if (bonusProject.isEmpty()) {
                return "0";
            } else {
                return bonusProject;
            }
        }

        public void setBonusProject(String bonusProject) {
            this.bonusProject = bonusProject;
        }

        public String getSocialInsurance() {
            if (socialInsurance == null) {
                return "0";
            } else if (socialInsurance.isEmpty()) {
                return "0";
            } else {
                return socialInsurance;
            }
        }

        public void setSocialInsurance(String socialInsurance) {
            this.socialInsurance = socialInsurance;
        }

        public String getSalary() {
            if (salary == null) {
                return "0";
            } else if (salary.isEmpty()) {
                return "0";
            } else {
                return salary;
            }
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getDescriptionWork() {
            return descriptionWork;
        }

        public void setDescriptionWork(String descriptionWork) {
            this.descriptionWork = descriptionWork;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getEmploymentId() {
            return employmentId;
        }

        public void setEmploymentId(String employmentId) {
            this.employmentId = employmentId;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        @NonNull
        @Override
        public String toString() {
            return "Contracts{" +
                    "bonusProject='" + bonusProject + '\'' +
                    ", socialInsurance='" + socialInsurance + '\'' +
                    ", salary='" + salary + '\'' +
                    ", descriptionWork='" + descriptionWork + '\'' +
                    ", content='" + content + '\'' +
                    ", createAt='" + createAt + '\'' +
                    ", employmentId='" + employmentId + '\'' +
                    ", _id='" + _id + '\'' +
                    '}';
        }
    }
}
