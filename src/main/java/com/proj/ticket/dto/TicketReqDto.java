package com.proj.ticket.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TicketReqDto {

	@NotNull
	@NotBlank(message = "userName is mandatory")
	@Size(min = 3, max = 30)
	private String userName;

	@NotNull
	@NotBlank(message = "source is mandatory")
	@Size(min = 3, max = 30)
	private String source;
	
	@NotNull
	@NotBlank(message = "destination is mandatory")
	@Size(min = 3, max = 30)
	private String destination;
	
	@NotNull
	@NotBlank(message = "departureDate is mandatory")
	private String departureDate;
	
	@NotNull(message = "noOfPassengers is mandatory")
	@Min(1)
	@Max(6)
	private Long noOfPassengers;
	
	@NotNull(message = "train number is mandatory")
	private Long trainNo;
	
	private List<PassengerDetailsDto> passgerDetails;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public Long getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(Long noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public Long getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(Long trainNo) {
		this.trainNo = trainNo;
	}

	public List<PassengerDetailsDto> getPassgerDetails() {
		return passgerDetails;
	}

	public void setPassgerDetails(List<PassengerDetailsDto> passgerDetails) {
		this.passgerDetails = passgerDetails;
	}

}
