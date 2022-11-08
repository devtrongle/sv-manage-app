package com.sbp.manage.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateEmploymentDto {
    @Expose
    @SerializedName("Employment")
    private Employment Employment;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("success")
    private boolean success;

    public Employment getEmployment() {
        return Employment;
    }

    public void setEmployment(Employment Employment) {
        this.Employment = Employment;
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
        return "CreateEmploymentDto{" +
                "Employment=" + Employment +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }

    public static class Employment {
        @Expose
        @SerializedName("_id")
        private String _id;
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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

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

        @NonNull
        @Override
        public String toString() {
            return "Employment{" +
                    "_id='" + _id + '\'' +
                    ", roleEmployment='" + roleEmployment + '\'' +
                    ", joinDate='" + joinDate + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", sex=" + sex +
                    ", birthday='" + birthday + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
