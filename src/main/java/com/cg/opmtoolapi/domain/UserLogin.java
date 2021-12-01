package com.cg.opmtoolapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="users")
public class UserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name",unique= true,updatable = false)
	@NotBlank(message="user name is required")
	private String userName;

	@Column(name = "email", unique=true,updatable= true)
	@Email
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Like xyz@abc.com")
	private String email;
	
    @NotBlank(message="Password is a required field")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "Must have Digits atleast one lower atleast one upper and a special character")
	private String password;
    
    private boolean isUser;
    
    private boolean isEmployee;
    
    private boolean isCompleted;
    
    public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLogin() {
		super();
	}


	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}
	
	public UserLogin(String userName,
			@NotBlank(message = "email is required") @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Enter like abc@abc.com") String email,
			@NotBlank(message = "Password is a required field") @Size(min = 8, max = 16, message = "Password must be equal to or greater than 8 characters and less then 16 characters") String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}


	public UserLogin(
			@NotBlank(message = "email is required") @Email @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") String email,
			@NotBlank(message = "Password is a required field") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Must have Digits atleast one lower atleast one upper and a special character") String password) {
		super();
		this.email = email;
		this.password = password;
	}



}
