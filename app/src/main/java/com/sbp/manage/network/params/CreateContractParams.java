package com.sbp.manage.network.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateContractParams {
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

    public String getBonusProject() {
        return bonusProject;
    }

    public void setBonusProject(String bonusProject) {
        this.bonusProject = bonusProject;
    }

    public String getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(String socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public String getSalary() {
        return salary;
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
}
