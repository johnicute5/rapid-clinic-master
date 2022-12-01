package com.example.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {


	// Auto-generated
	private String id;

	// Auto-generated
	private String icNo;

	// Auto-generated
	private String password;
	
	// Auto-generated
	private String passwordResetToken;

	// Auto-generated
	private Date registrationDate = new Date();
	
    private boolean isFirstLogin;

	@NotBlank(message = "Email must not be empty!")
	@Email
	private String email;

	@NotBlank(message = "Username must not be empty!")
	@Size(min = 5, max = 15, message = "Username must be between 5 - 15 characters only!")
	private String username;

	@NotBlank(message = "First Name must not be empty!")
	@Size(min = 2, message = "First Name must be at least 2 characters!")
	private String firstName;

	// Optional
	private String middleName;

	@NotBlank(message = "Last Name must not be empty!")
	@Size(min = 2, message = "Last Name must be at least 2 characters!")
	private String lastName;

	@NotBlank(message = "Date must not be empty!")
	private String birthday;

	@NotEmpty(message = "Select your gender!")
	private String gender;

	@NotBlank(message = "Address must not be empty!")
	private String address;

	@NotBlank(message = "Contact Number must not be empty!")
	@Size(min = 11, max = 11, message = "Contact Number must be exactly 11 characters only!")
	private String contactNo;

	@NotEmpty(message = "Select your position!")
	private String role;

//	private byte[] imageData;

	// no arg constructor
	public User() {
	}

	// constructor without id
	public User(String icNo, String password, Date registrationDate,
			@NotBlank(message = "Email must not be empty!") @Email String email,
			@NotBlank(message = "Username must not be empty!") @Size(min = 5, max = 15, message = "Username must be between 5 - 15 characters only!") String username,
			@NotBlank(message = "First Name must not be empty!") @Size(min = 2, message = "First Name must be at least 2 characters!") String firstName,
			String middleName,
			@NotBlank(message = "Last Name must not be empty!") @Size(min = 2, message = "Last Name must be at least 2 characters!") String lastName,
			@NotBlank(message = "Date must not be empty!") String birthday, @NotNull String gender,
			@NotBlank(message = "Address must not be empty!") String address,
			@NotBlank(message = "Contact Number must not be empty!") @Size(min = 11, max = 11, message = "Contact Number must be exactly 11 characters only!") String contactNo,
			@NotNull String role) {
		this.icNo = icNo;
		this.password = password;
		this.registrationDate = registrationDate;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.contactNo = contactNo;
		this.role = role;
	}

	// constructor with id
	public User(String id, String icNo, String password, Date registrationDate,
			@NotBlank(message = "Email must not be empty!") @Email String email,
			@NotBlank(message = "Username must not be empty!") @Size(min = 5, max = 15, message = "Username must be between 5 - 15 characters only!") String username,
			@NotBlank(message = "First Name must not be empty!") @Size(min = 2, message = "First Name must be at least 2 characters!") String firstName,
			String middleName,
			@NotBlank(message = "Last Name must not be empty!") @Size(min = 2, message = "Last Name must be at least 2 characters!") String lastName,
			@NotBlank(message = "Date must not be empty!") String birthday, @NotNull String gender,
			@NotBlank(message = "Address must not be empty!") String address,
			@NotBlank(message = "Contact Number must not be empty!") @Size(min = 11, max = 11, message = "Contact Number must be exactly 11 characters only!") String contactNo,
			@NotNull String role) {
		super();
		this.id = id;
		this.icNo = icNo;
		this.password = password;
		this.registrationDate = registrationDate;
		this.email = email;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.contactNo = contactNo;
		this.role = role;
	}

	// setters and getters


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcNo() {
		return icNo;
	}

	public void setIcNo(String icNo) {
		this.icNo = icNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role.toUpperCase();
	}
	
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getInitials() {
        return firstName.charAt(0) + "" + lastName.charAt(0);
    }


	@Override
	public String toString() {
		return "User [id=" + id + ", icNo=" + icNo + ", password=" + password + ", passwordResetToken="
				+ passwordResetToken + ", registrationDate=" + registrationDate + ", email=" + email + ", username="
				+ username + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", birthday=" + birthday + ", gender=" + gender + ", address=" + address + ", contactNo=" + contactNo
				+ ", role=" + role + "]";
	}
	
	

}
