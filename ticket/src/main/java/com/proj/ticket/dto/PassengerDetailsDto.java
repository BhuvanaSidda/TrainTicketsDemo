package com.proj.ticket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PassengerDetailsDto {

	@NotNull
	@NotBlank(message = "passengerName is mandatory")
	@Size(min = 3, max = 30)
	private String passengerName;

	@NotNull
	@NotBlank(message = "passengerGender is mandatory")
	@Size(min = 1)
	private Character passengerGender;

	@NotNull(message = "Age is mandatory")
	private Long age;

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Character getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(Character passengerGender) {
		this.passengerGender = passengerGender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
