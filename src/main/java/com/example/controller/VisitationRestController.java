package com.example.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Appointment;
import com.example.model.Patient;
import com.example.model.Visitation;
import com.example.service.AppointmentService;
import com.example.service.VisitationService;

@RestController
@RequestMapping(path ="api/visit")
public class VisitationRestController {
	
	private final VisitationService visitService;


	@Autowired
	public VisitationRestController(VisitationService visitService) {
		this.visitService = visitService;

	}
	
	//Get All Visits
	@GetMapping("/")
	public List<Visitation> getAllVisits () throws InterruptedException, ExecutionException {
		return visitService.getAllVisits();
	}

	//Add Visitation
	@PostMapping("/add")
	public String addVisitation(@RequestBody Visitation visit) throws InterruptedException, ExecutionException {
		System.out.println("visitation controller has been successfully created");
		return visitService.addVisitation(visit);
	}
	
	 @GetMapping("/userAppointment")
	    public Visitation getAppointmentByPatientId(@RequestParam String id) throws InterruptedException, ExecutionException {
	        return visitService.getAppointmentById(id);
	  }
	 
	
	//Get Visitation by ID
	@GetMapping("/getById")
	public Visitation getVisitbyId(@RequestParam String medicalCertificateID) throws InterruptedException, ExecutionException {
		return visitService.getVisitbyId(medicalCertificateID);
	}
	
	@GetMapping("/edit")
	public String editVisit(@RequestBody Visitation visit) throws InterruptedException, ExecutionException {
		return visitService.editVisit(visit);
	}
	
	
	@GetMapping("/test")
	public ResponseEntity<String> testGetEndpoint(){
		return ResponseEntity.ok("Test Get Endpoint is Working");
	}

}
