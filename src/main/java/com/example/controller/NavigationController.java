package com.example.controller;

import com.example.auth.CustomUserDetails;
import com.example.model.Appointment;
import com.example.model.User;
import com.example.service.EmailSenderService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Controller
public class NavigationController {

	@Autowired
	UserService userService;
	
    @Autowired
    private EmailSenderService senderService;

	@GetMapping("/{*}")
	public String notFound() {
		return "not_found_404";
	}

	@GetMapping("/")
	public String getIndex() {
		return "dashboard";
	}
	
    @GetMapping("/login")
    public String viewLoginPage(){
        return "login";
    }

	// Dashboard Controller
	@Autowired
	private AppointmentRestController appointmentController;

	@GetMapping("/dashboard")
	public ModelAndView getDashboard(ModelMap model, Appointment date) throws ExecutionException, InterruptedException {
		UserDetails userDetails = userService.getCurrentUser();
		User user = userService.getUserByUsername(userDetails.getUsername());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		if (user.isFirstLogin()) {
			return new ModelAndView("redirect:/change_password");
		}
		List<Appointment> list = appointmentController.getAllAppointments();
		List<Appointment> userList = appointmentController.getAppointmentByPatientId(user.getId());
		System.out.println("Patient Id " + user.getId());
		System.out.println(userList);
		model.addAttribute("userListOfAppointments", userList);

		System.out.println("doctor " + list.stream()
		.filter(e ->  e.getNurse().equals(customUserDetails.getFullName()))
		.collect(Collectors.toList()));
		
		model.addAttribute("doctorAppointment",list.stream()
				.filter(e ->  e.getNurse().equals(customUserDetails.getFullName()))
				.collect(Collectors.toList()) );
		model.addAttribute("listOfAppointments", list);
		model.addAttribute("listOfAppointmentDate",
				list.stream().map(Appointment::getDate).distinct().collect(Collectors.toList()));
		return new ModelAndView("dashboard");
	}

	// Profile Controller
	@GetMapping("/profile")
	public String getProfile() {
		return "profile";
	}
	
	// Register Controller
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) throws ExecutionException, InterruptedException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFirstLogin(true);
       /* userRepo.save(user);*/
        userService.addUser(user);
        String url = "http://localhost:8080/change_password";
        senderService.sendEmail(user.getEmail(),
                "User Credentials",
                "Registration sucessful\n\nyour username is: " + user.getUsername() +
                        "\npass is: "+user.getLastName()+"MMDDYYY\n\nClick here to change password:\n"+url);

        return "register_success";
    }

}