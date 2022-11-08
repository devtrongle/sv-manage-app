package com.sbp.manage.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginDto {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("isAdmin")
    private boolean isAdmin;

    public LoginDto(boolean success, String message, boolean isAdmin) {
        this.success = success;
        this.message = message;
        this.isAdmin = isAdmin;
    }

    public LoginDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginDto{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
