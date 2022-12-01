package com.example.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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

import com.example.model.Patient;
import com.example.model.User;
import com.example.service.EmailSenderService;

@Controller
public class PatientController {

	// Patient Controller
	@Autowired
	private UserRestController userRestController;

	@Autowired
	private EmailSenderService senderService;

	@GetMapping("/patient")
	public String getAllPatient(@ModelAttribute("patient") Patient patient, String firstName, ModelMap model)
			throws InterruptedException, ExecutionException {
		if (firstName != null) {
//				Patient list = userRestController.getPatientByName(firstName);
//				model.addAttribute("patient", list);

		} else {
			List<User> list = userRestController.getAllUsers();
			List<User> patientList = list.stream().filter(p -> p.getRole().equalsIgnoreCase("PATIENT"))
					.collect(Collectors.toList());
			patientList.sort(Comparator.comparing(User::getRegistrationDate));
			model.addAttribute("patient", patientList);

		}
		return "/patient";
	}

	@GetMapping("/patient_form")
	public String getPatientForm(Model model) {
		model.addAttribute("patient", new Patient());

		List<String> listGender = Arrays.asList("Male", "Female");
		model.addAttribute("listGender", listGender);


		return "patient_form";
	}

	@PostMapping("/patient_action")
	public String submitPatientForm(@Validated @ModelAttribute("patient") Patient patient, BindingResult result,
			ModelMap model) throws InterruptedException, ExecutionException {
		if (result.hasErrors()) {

			List<String> listGender = Arrays.asList("Male", "Female");
			model.addAttribute("listGender", listGender);
			String role = "PATIENT";
			model.addAttribute("role", role);
			System.out.println("Error: " + result);
			return "patient_form";
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (patient.getPassword() != null) {
			String encodedPassword = passwordEncoder.encode(patient.getPassword());
			patient.setPassword(encodedPassword);
		} else {
			String encodedPassword = passwordEncoder.encode(patient.getLastName() + patient.getBirthday());
			patient.setPassword(encodedPassword);
		}

		int randomNumber = ((int) (Math.random() * 9000) + 1000);
		patient.setFirstLogin(true);
		patient.setIcNo("22-" + patient.getBirthday() + randomNumber);

		userRestController.addUser(patient);

		String url = "http://localhost:8080/change_password";
		senderService.sendEmail(patient.getEmail(), "User Credentials",
				"Registration sucessful\n\nYour username is: " + patient.getUsername() + "\nYour password is: "
						+ patient.getLastName() + patient.getBirthday() + "\n\nClick here to change password:\n" + url);

		return "redirect:/patient";
	}

	@GetMapping("/patient_edit/{id}")
	public String updateForm(@PathVariable(value = "id") String id, Model model)
			throws InterruptedException, ExecutionException {
		User patient = userRestController.getUserbyId(id);
		model.addAttribute("patient", patient);

		List<String> listGender = Arrays.asList("Male", "Female");
		model.addAttribute("listGender", listGender);
		return "patient_edit";
	}

	@PostMapping("/patient_edit_action")
	public String submitUpdatedForm(@Validated @ModelAttribute("patient") Patient patient, BindingResult result,
			ModelMap model) throws InterruptedException, ExecutionException {
		if (result.hasErrors()) {

			List<String> listGender = Arrays.asList("Male", "Female");
			model.addAttribute("listGender", listGender);

			return "patient_edit";
		}
		userRestController.editUser(patient);
		return "redirect:/patient";
	}

}
