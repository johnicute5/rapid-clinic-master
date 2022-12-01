package com.example.controller;

import com.example.model.Appointment;
import com.example.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/appointment")
public class AppointmentRestController {

    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @RequestMapping( "/")
    public String createAppointment(@ModelAttribute Appointment appointment) throws InterruptedException, ExecutionException {
        System.out.println("appointment controller create");
        return appointmentService.createAppointment(appointment);
    }
    @GetMapping("/")
    public List<Appointment> getAllAppointments() throws InterruptedException, ExecutionException {
        System.out.println("appointment controller get");
        return appointmentService.getAppointments();
    }

    @GetMapping("/userAppointment")
    public List<Appointment> getAppointmentByPatientId(@RequestParam String id) throws InterruptedException, ExecutionException {
        return appointmentService.getAppointmentByPatientId(id);
    }
    @GetMapping("/search")
    public Appointment getAppointmentByName(@RequestParam String name) throws InterruptedException, ExecutionException {
        return appointmentService.getAppointmentByName(name);
    }

    @GetMapping("/searchDat")
    public List<Appointment> getAppointmentByDate(@RequestParam String date) throws InterruptedException, ExecutionException {
        return appointmentService.getAppointmentByDate(date);
    }

    @DeleteMapping("/delete")
    public String deleteAppointment(@RequestParam String id) throws InterruptedException, ExecutionException {
        return appointmentService.deleteAppointment(id);
    }

}
