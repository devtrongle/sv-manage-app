package com.sbp.manage.network.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEmploymentParams {
    @Expose
    @SerializedName("roleEmployment")
    private String roleEmployment;
    @Expose
    @SerializedName("joinDate")
    private String joinDate;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("sex")
    private boolean sex;
    @Expose
    @SerializedName("birthday")
    private String birthday;
    @Expose
    @SerializedName("name")
    private String name;

    public String getRoleEmployment() {
        return roleEmployment;
    }

    public void setRoleEmployment(String roleEmployment) {
        this.roleEmployment = roleEmployment;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
