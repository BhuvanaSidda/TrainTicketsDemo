package com.proj.gateway.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserReqDto {

	@NotNull
	@NotBlank(message = "userName is mandatory")
	@Size(min = 3, max = 30)
	private String userName;

	@NotNull
	@NotBlank(message = "password is mandatory")
	@Size(min = 3, max = 30)
	private String password;

	@NotNull
	@NotBlank(message = "firstName is mandatory")
	@Size(min = 3, max = 30)
	private String firstName;

	private String lastName;

	private String gender;

	@Min(18)
	@Max(60)
	private Long age;

	private String mobileNo;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}