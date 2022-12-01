package com.example.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Visitation {
	
	private String medicalCertificateID;
	private String name;
	private String icNo;
	
	@NotBlank(message = "Reason must not be empty!")
    @Size(min = 3, message = "reason should have at least 4 characters!")
	private String Reason;
	
	@NotBlank(message = "time must not be empty!")
    @NotNull(message = "time must not be empty!")
	private String time;
	
	@NotBlank(message = "date must not be empty!")
    @NotNull(message = "date must not be empty!")
	private String date = "2022-11-28";
//	DateFormat format = new SimpleDateFormat("EEEE"); 
	
	LocalDate localDate = LocalDate.of(Integer.parseInt(date, 0, 3, 9),
			Integer.parseInt(date, 5, 6, 9),
			Integer.parseInt(date, 8, 9, 9));
    java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
	@NotBlank(message = "day must not be empty!")
    @NotNull(message = "day must not be empty!")
	private String Day = dayOfWeek.toString();
//	= new SimpleDateFormat("EEEE").format(date);
//	= format.format(date);
//	String dayWeekText = 
	
	
	/*@Transient()*/
    private String visitationDate = LocalDateTime.now().toString();
    
	public Visitation() {
		super();
	}

	public Visitation(String medicalCertificateID, String date, String day, String reason, String name,
			String icNo, String time, String visitationDate) {
		this.medicalCertificateID = medicalCertificateID;
		this.date = date;
		Day = day;
		Reason = reason;
		this.name = name;
		this.icNo = icNo;
		this.time = time;
		this.visitationDate = visitationDate;
	}
	

	public Visitation(String date, String day, String reason, String name, String icNo, String time, String visitationDate) {
		this.date = date;
		Day = day;
		Reason = reason;
		this.name = name;
		this.icNo = icNo;
		this.time = time;
		this.visitationDate = visitationDate;
	}



	public String getMedicalCertificateID() {
		return medicalCertificateID;
	}

	public void setMedicalCertificateID(String medicalCertificateID) {
		this.medicalCertificateID = medicalCertificateID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
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
	
	@Override
	public String toString() {
		return "Visitation [MedicalCertificateID=" + medicalCertificateID + ", startDate=" + date + ", Day=" + Day
				+ ", Reason=" + Reason + ", name=" + name + ", icNo=" + icNo + ", time=" + time + "]";
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVisitationDate() {
		return visitationDate;
	}

	public void setVisitationDate(String visitationDate) {
		this.visitationDate = visitationDate;
	}
	

}
