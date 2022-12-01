package com.example.controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.auth.CustomUserDetails;
import com.example.model.Appointment;
import com.example.model.User;

@Controller
public class AppointmentController {

	// Appointment Controller
	@Autowired
	private AppointmentRestController appointmentController;

	@GetMapping("/appointment")
	public ModelAndView getAllAppointments(ModelMap model, Appointment date)
			throws InterruptedException, ExecutionException {
		List<Appointment> list = appointmentController.getAllAppointments();
		list.sort(Comparator.comparing(Appointment::getAppointmentDate));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		
		System.out.println("This is the doctor's list of appointments : "+list.stream()
		.filter(e ->  e.getNurse().equals(customUserDetails.getFullName()))
		.collect(Collectors.toList()));

		//use this to list all appointments for nurse and Admin's dashboard
		model.addAttribute("listOfAllAppointments",list);
		List<Appointment> arrayList = list.stream()
				.filter(e ->  e.getPatientId().equals(customUserDetails.getId()))
				.collect(Collectors.toList());
		list.sort(Comparator.comparing(Appointment::getAppointmentDate));
		
		
		model.addAttribute("listOfAllAppointmentDate",list.
				stream().map(Appointment::getDate)
				.distinct().collect(Collectors.toList()));
//		System.out.println("Current user date "+arrayList
//				.stream().map(Appointment::getDate).distinct().collect(Collectors.toList()));
//		System.out.println(arrayList
//				.stream().map(Appointment::getDoctor).distinct().collect(Collectors.toList()));

		//use this to filter current user's appointment's list patient dashboard
		model.addAttribute("listOfCurrentUserAppoint",arrayList);
		model.addAttribute("listOfCurrentUserAppointmentDate",arrayList
				.stream().map(Appointment::getDate).distinct().collect(Collectors.toList()));
		
		return new ModelAndView("appointment");
	}


	@Autowired
	private UserRestController adminSystemController;
	
	@GetMapping("/add_appointment")
	public String getAppointmentForm(Model model) throws ExecutionException, InterruptedException {
		Appointment appointment = new Appointment();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		System.out.println("IcNO :" +customUserDetails.getIcNo());
		List<User> list = adminSystemController.getAllUsers();
		model.addAttribute("listNurse",list.stream()
				.filter(e -> e.getRole().equals("NURSE"))
				.map(User::getFullName)
				.collect(Collectors.toList()));
		System.out.println("Nurses:" +list.stream()
				.filter(e -> e.getRole().equals("NURSE"))
				.map(User::getFullName)
				.collect(Collectors.toList()));
		model.addAttribute("appointments", appointment);
		return "appointment_form";
	}

	@GetMapping("/notification")
	public String getNotification() {
		return "notification";
	}

	@PostMapping("/appointment_action")
	public String submitAppointmentForm(@Validated @ModelAttribute("appointments") Appointment appointment,
			BindingResult result, ModelMap model) throws ExecutionException, InterruptedException {
		System.out.println(appointment.toString());
		if (result.hasErrors()) {
			return "appointment_form";

		}
		appointmentController.createAppointment(appointment);
		return "redirect:/dashboard";
	}

}
