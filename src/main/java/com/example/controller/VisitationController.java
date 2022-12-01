package com.example.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.model.Patient;
import com.example.model.Visitation;

@Controller
public class VisitationController {
	// Visitation Controller
	@Autowired
	private VisitationRestController visitationController;
	
	

	@GetMapping("/visitation")
	public ModelAndView getAllVisits(@ModelAttribute("visit") Visitation visit, String medicalCertificateID , ModelMap model) throws InterruptedException, ExecutionException {
		
		if( medicalCertificateID != null) {
			Visitation list = visitationController.getVisitbyId(medicalCertificateID);
			model.addAttribute("listOfVisits", list);
			
		}else {
		
		List<Visitation> list = visitationController.getAllVisits();
		model.addAttribute("listOfVisits", list);
		}
		return new ModelAndView("visitation");
	}

	@GetMapping("/visitation_report/{appointmentId}")
	public String getVisitationAddForm(Model model ,@PathVariable String appointmentId) throws InterruptedException, ExecutionException {

		Visitation visit = visitationController.getAppointmentByPatientId(appointmentId);
		String date = "2022-11-28";
		LocalDate localDate = LocalDate.of(Integer.parseInt(date, 0, 3, 9),
				Integer.parseInt(date, 5, 6, 9),
				Integer.parseInt(date, 8, 9, 9));
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println(visit);
		System.out.println(dayOfWeek.toString());
		model.addAttribute("visit", visit);

		return "visitation_report";
	}

	@PostMapping("/visitation_add_action")
	public String submitVisitationForm(@Validated @ModelAttribute("visit") Visitation visit, BindingResult result,
			ModelMap model) throws InterruptedException, ExecutionException {
		visitationController.addVisitation(visit);
		return "visitation_success";
	}

	@GetMapping("/visitation_success")
	public String getVisitationSuccess() {
		return "visitation_success";
	}

	@GetMapping("/visitation_edit/{medicalCertificateID}")
	public String getVisitationEdit(Model model, @PathVariable String medicalCertificateID)
			throws InterruptedException, ExecutionException {

		Visitation visit = visitationController.getVisitbyId(medicalCertificateID);
		model.addAttribute("visit", visit);

		List<String> listRoles = Arrays.asList("Administrator", "Doctor", "Nurse");
		model.addAttribute("listRoles", listRoles);

		List<String> listGender = Arrays.asList("Male", "Female");
		model.addAttribute("listGender", listGender);

		return "visitation_edit";
	}

	@PostMapping("/visitation_editAction/{medicalCertificateID}")
	public String submitEditVisitation(@Validated @ModelAttribute("visit") Visitation visit,
			@PathVariable String medicalCertificateID, BindingResult result, ModelMap model)
			throws InterruptedException, ExecutionException {

		if (result.hasErrors()) {

			List<String> listRoles = Arrays.asList("Administrator", "Doctor", "Nurse");
			model.addAttribute("listRoles", listRoles);

			List<String> listGender = Arrays.asList("Male", "Female");
			model.addAttribute("listGender", listGender);

			return "visitation_edit/{medicalCertificateID}";
		}

		visitationController.editVisit(visit);

		return "redirect:/visitation";
	}
	
	@GetMapping("/visitation_details/{medicalCertificateID}")
	public String getVisitationDetails(Model model, @PathVariable String medicalCertificateID) throws InterruptedException, ExecutionException {

		Visitation visit = visitationController.getVisitbyId(medicalCertificateID);
		String date = "2022-11-28";
		LocalDate localDate = LocalDate.of(Integer.parseInt(date, 0, 3, 9),
				Integer.parseInt(date, 5, 6, 9),
				Integer.parseInt(date, 8, 9, 9));
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println(visit);
		System.out.println(dayOfWeek.toString());
		model.addAttribute("visit", visit);

		return "visitation_details";
	}

}
