package com.sbp.manage.network.dto;

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
    @SerializedName("email")
    private String email;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", isAdmin=" + isAdmin +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
