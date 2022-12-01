package com.example.model;


import com.example.auth.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class Appointment {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    private String appointmentID;
    private String name =  customUserDetails.getFullName();

    private String patientId = customUserDetails.getId();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status = "Pending";


    @NotBlank(message = "nurse must not be empty!")
    @NotNull(message = "nurse must not be empty!")
    private String nurse;

    private String icNo = customUserDetails.getIcNo();

    @NotBlank(message = "Remark must not be empty!")
    @Size(min = 3, message = "remark must be have at least 4 characters!")
    private String remark;

    @NotBlank(message = "time must not be empty!")
    @NotNull(message = "time must not be empty!")
    private String time;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "date must not be empty!")
    @NotNull(message = "date must not be empty!")
    private String date;

    private String appointmentDate = LocalDateTime.now().toString();
       
    public String getNurse() {
        return nurse;
    }
    
    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }



    public Appointment() {
        super();
    }
    public Appointment(String appointmentID,String patientId ,String isApproved,String nurse,String name, String icNo, String remark, String time, String date, String appointmentDate) {
        this.appointmentID = appointmentID;
        this.patientId =  patientId;
        this.status = isApproved;
        this.name = name;
        this.icNo = icNo;
        this.nurse = nurse;
        this.remark = remark;
        this.time = time;
        this.date = date;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(String name,String patientId, String isApproved, String icNo, String nurse,String remark, String time, String date, String appointmentDate) {
        this.name = name;
        this.status = isApproved;
        this.icNo = icNo;
        this.patientId =  patientId;
        this.nurse = nurse;
        this.remark = remark;
        this.time = time;
        this.date = date;
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment: " +
                "appointmentID='" + getAppointmentID() + '\'' +
                ", name='" + name + '\'' +
                ", icNo='" + icNo + '\'' +
                ", remark= '" + remark + '\'' +
                ", isApproved='" + status +
                ",Nurse= '" + nurse + '\''+
                "appointment registration date= '" + appointmentDate + '\'' +
                ",appointment schedule date=" + date +
                ", time=" + time
                + " ";
    }
}
