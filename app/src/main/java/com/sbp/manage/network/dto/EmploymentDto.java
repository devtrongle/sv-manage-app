package com.sbp.manage.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EmploymentDto {
    @Expose
    @SerializedName("employment")
    private List<Employment> employment;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;

    public List<Employment> getEmployment() {
        return employment;
    }

    public void setEmployment(List<Employment> employment) {
        this.employment = employment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @NonNull
    @Override
    public String toString() {
        return "EmploymentDto{" +
                "employment=" + employment +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }

    public static class Employment implements Serializable {
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
        @Expose
        @SerializedName("_id")
        private String _id;

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

        public boolean isSex() {
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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        @NonNull
        @Override
        public String toString() {
            return "Employment{" +
                    "roleEmployment='" + roleEmployment + '\'' +
                    ", joinDate='" + joinDate + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", sex=" + sex +
                    ", birthday='" + birthday + '\'' +
                    ", name='" + name + '\'' +
                    ", _id='" + _id + '\'' +
                    '}';
        }
    }
}
