package com.sbp.manage.network.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LoginDto {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;

    public LoginDto(boolean success, String message) {
        this.success = success;
        this.message = message;
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

    @NonNull
    @Override
    public String toString() {
        return "LoginDto{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
