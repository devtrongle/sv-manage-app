package com.sbp.manage.network.dto;


public class ContractDto {
    private String _id;
    private String employmentId;
    private String createAt;
    private String content;
    private String descriptionWork;
    private String salary;
    private String socialInsurance;
    private String bonusProject;
    private float __v;


// Getter Methods

    public String get_id() {
        return _id;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getContent() {
        return content;
    }

    public String getDescriptionWork() {
        return descriptionWork;
    }

    public String getSalary() {
        return salary;
    }

    public String getSocialInsurance() {
        return socialInsurance;
    }

    public String getBonusProject() {
        return bonusProject;
    }

    public float get__v() {
        return __v;
    }

// Setter Methods

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDescriptionWork(String descriptionWork) {
        this.descriptionWork = descriptionWork;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSocialInsurance(String socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public void setBonusProject(String bonusProject) {
        this.bonusProject = bonusProject;
    }

    public void set__v(float __v) {
        this.__v = __v;
    }
}