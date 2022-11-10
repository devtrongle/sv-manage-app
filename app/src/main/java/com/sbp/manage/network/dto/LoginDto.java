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
    @SerializedName("username")
    private String username;

    public LoginDto(boolean success, String message, boolean isAdmin, String username) {
        this.success = success;
        this.message = message;
        this.isAdmin = isAdmin;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
