package com.example.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ResetPassword {

    public ResetPassword(){

    }

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 6, message = "Password must be at least 6 characters!")
    private String password;

    @NotBlank(message = "Confirm your password!")
    private String confirmPassword;

    public ResetPassword(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
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
