package com.example.model;


import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Patient extends User {

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String icNo, String password, Date registrationDate,
			@NotBlank(message = "Email must not be empty!") @Email String email,
			@NotBlank(message = "Username must not be empty!") @Size(min = 5, max = 15, message = "Username must be between 5 - 15 characters only!") String username,
			@NotBlank(message = "First Name must not be empty!") @Size(min = 2, message = "First Name must be at least 2 characters!") String firstName,
			String middleName,
			@NotBlank(message = "Last Name must not be empty!") @Size(min = 2, message = "Last Name must be at least 2 characters!") String lastName,
			@NotBlank(message = "Date must not be empty!") String birthday, @NotNull String gender,
			@NotBlank(message = "Address must not be empty!") String address,
			@NotBlank(message = "Contact Number must not be empty!") @Size(min = 11, max = 11, message = "Contact Number must be exactly 11 characters only!") String contactNo,
			@NotNull String role) {
		super(icNo, password, registrationDate, email, username, firstName, middleName, lastName, birthday, gender, address,
				contactNo, role);
		// TODO Auto-generated constructor stub
		this.setRole("PATIENT");
	}

	public Patient(String id, String icNo, String password, Date registrationDate,
			@NotBlank(message = "Email must not be empty!") @Email String email,
			@NotBlank(message = "Username must not be empty!") @Size(min = 5, max = 15, message = "Username must be between 5 - 15 characters only!") String username,
			@NotBlank(message = "First Name must not be empty!") @Size(min = 2, message = "First Name must be at least 2 characters!") String firstName,
			String middleName,
			@NotBlank(message = "Last Name must not be empty!") @Size(min = 2, message = "Last Name must be at least 2 characters!") String lastName,
			@NotBlank(message = "Date must not be empty!") String birthday, @NotNull String gender,
			@NotBlank(message = "Address must not be empty!") String address,
			@NotBlank(message = "Contact Number must not be empty!") @Size(min = 11, max = 11, message = "Contact Number must be exactly 11 characters only!") String contactNo,
			@NotNull String role) {
		super(id, icNo, password, registrationDate, email, username, firstName, middleName, lastName, birthday, gender, address,
				contactNo, role);
		// TODO Auto-generated constructor stub
		this.setRole("PATIENT");
	}


}
