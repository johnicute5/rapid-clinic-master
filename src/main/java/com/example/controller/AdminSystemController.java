package com.example.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Staff;
import com.example.model.User;
import com.example.service.EmailSenderService;
import com.example.service.UserService;

@Controller
public class AdminSystemController {

	// Admin System Controller
	@Autowired
	private UserRestController userRestController;

	@Autowired
	private EmailSenderService senderService;

	@GetMapping("/admin_system")
	public ModelAndView getAdminSystem(ModelMap model) throws InterruptedException, ExecutionException {
		List<User> list = userRestController.getAllUsers();
		List<User> adminList =  list.stream()
				.filter(p -> p.getRole().equalsIgnoreCase("ADMIN") 
						|| p.getRole().equalsIgnoreCase("DOCTOR")
						|| p.getRole().equalsIgnoreCase("NURSE")) 
				.collect(Collectors.toList());
		adminList.sort(Comparator.comparing(User::getRegistrationDate));
		model.addAttribute("listOfStaff", adminList);

		return new ModelAndView("admin_system");
	}

	@GetMapping("/admin_system_add")
	public String getAdminSystemAdd(Model model) {

		Staff staff = new Staff();
		model.addAttribute("staff", staff);

		List<String> listRoles = Arrays.asList("Admin", "Doctor", "Nurse");
		model.addAttribute("listRoles", listRoles);

		List<String> listGender = Arrays.asList("Male", "Female");
		model.addAttribute("listGender", listGender);

		return "admin_system_add";
	}

	@PostMapping("/admin_system_addAction")
	public String submitAddStaffForm(@Validated @ModelAttribute("staff") Staff staff, BindingResult result,
			ModelMap model) throws InterruptedException, ExecutionException {
		if (result.hasErrors()) {

			List<String> listRoles = Arrays.asList("Admin", "Doctor", "Nurse");
			model.addAttribute("listRoles", listRoles);

			List<String> listGender = Arrays.asList("Male", "Female");
			model.addAttribute("listGender", listGender);

			return "admin_system_add";
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (staff.getPassword() != null) {
			String encodedPassword = passwordEncoder.encode(staff.getPassword());
			staff.setPassword(encodedPassword);
		} else {
			String encodedPassword = passwordEncoder.encode(staff.getLastName() + staff.getBirthday());
			staff.setPassword(encodedPassword);
		}

		int randomNumber = ((int)(Math.random()*9000)+1000);
		staff.setFirstLogin(true);
		staff.setIcNo("22-" + staff.getBirthday() + randomNumber);

		userRestController.addUser(staff);
		String url = "http://localhost:8080/change_password";
		senderService.sendEmail(staff.getEmail(), "User Credentials",
				"Registration sucessful\n\nYour username is: " + staff.getUsername() + "\nYour password is: "
						+ staff.getLastName() + staff.getBirthday() + "\n\nClick here to change password:\n" + url);

		return "redirect:/admin_system";
	}

	@GetMapping("/admin_system_edit/{id}")
	public String getAdminSystemEdit(Model model, @PathVariable String id)
			throws InterruptedException, ExecutionException {

		User staff = userRestController.getUserbyId(id);
		model.addAttribute("staff", staff);

		List<String> listRoles = Arrays.asList("Admin", "Doctor", "Nurse");
		model.addAttribute("listRoles", listRoles);

		List<String> listGender = Arrays.asList("Male", "Female");
		model.addAttribute("listGender", listGender);

		return "admin_system_edit";
	}

	@PostMapping("/admin_system_editAction/{id}")
	public String submitEditStaffForm(@Validated @ModelAttribute("staff") Staff staff, @PathVariable String id,
			BindingResult result, ModelMap model) throws InterruptedException, ExecutionException {

		if (result.hasErrors()) {

			List<String> listRoles = Arrays.asList("Admin", "Doctor", "Nurse");
			model.addAttribute("listRoles", listRoles);

			List<String> listGender = Arrays.asList("Male", "Female");
			model.addAttribute("listGender", listGender);

			return "admin_system_edit/{id}";
		}

		userRestController.editUser(staff);

		return "redirect:/admin_system";
	}

}
