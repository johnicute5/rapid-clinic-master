package com.example.controller;

import com.example.model.ChangePassword;
import com.example.utils.Utility;
import com.example.model.ResetPassword;
import com.example.model.User;
import com.example.service.EmailSenderService;
import com.example.service.UserService;
import com.example.utils.GenerateResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    GenerateResetToken generateResetToken;

    @GetMapping("/access_denied")
    public String accessDeniedPage(){
        return "forbidden";
    }

    @GetMapping("/change_password")
    public String changePassword(){
        return "change_password";
    }

    @PostMapping("/change_password")
    public String processChangePassword(ChangePassword changePassword, BindingResult result,
        HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        if(result.hasErrors()) {
            return "change_password";
        }
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        UserDetails userDetails = userService.getCurrentUser();
        User user = userService.getUserByUsername(userDetails.getUsername());

        if (user == null) {
            model.addAttribute("errorMessage", "User does not exist");
            return "errorMessage";
        } else {
            if(bcrypt.matches(oldPassword, user.getPassword())){
                if(!oldPassword.equals(password)){
                    if(password.equals(confirmPassword)) {
                        userService.updatePassword(user, password);
                    } else {
                        model.addAttribute("errorMessage", "Password do not match");
                        return "change_password";
                    }
                }
                else {
                    model.addAttribute("errorMessage", "You are trying to use same password");
                    return "change_password";
                }
            } else {
                model.addAttribute("errorMessage", "Old password is incorrect");
                return "change_password";
            }
            model.addAttribute("result", true);
        }

        return "change_password";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = generateResetToken.generateResetPasswordToken();
        try {
            userService.updateResetPasswordToken(token.toString(), email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            String body = "Click the link to reset your password\n"+resetPasswordLink;
            emailSenderService.sendEmail(email, "Reset Password", body);
            model.addAttribute("successMessage", "We have sent a reset password link to your email. Please check.");

        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
        }

        return "forgot_password_form";
    }
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("token", token);
        model.addAttribute("changePassword", new ResetPassword());
        if (user == null) {
            model.addAttribute("errorMessage", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }
    @PostMapping("/reset_password")
    public String processResetPassword(@Validated @ModelAttribute("changePassword") ResetPassword changePassword, BindingResult result,
                                       HttpServletRequest request, Model model) throws ExecutionException, InterruptedException {
        if(result.hasErrors()) {
            return "reset_password_form";
        }

        String token = request.getParameter("token");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = userService.getUserByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("errorMessage", "Invalid Token");
            return "message";
        } else {
            if(password.equals(confirmPassword)) {
                userService.updatePassword(user, password);
            } else {
                model.addAttribute("errorMessage", "Password do not match");
                return "reset_password_form";
            }
            model.addAttribute("successMessage", "You have successfully changed your password.");
        }

        return "redirect:/login";
    }
}
