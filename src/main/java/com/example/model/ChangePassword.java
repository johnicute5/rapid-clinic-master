package com.example.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePassword {

    public ChangePassword(){

    }

    @NotBlank(message = "Please input your old password")
    private String oldPassword;

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @NotBlank(message = "Confirm your password!")
    private String confirmPassword;

    public ChangePassword(String oldPassword, String password, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
