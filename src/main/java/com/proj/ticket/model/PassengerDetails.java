package com.proj.ticket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PassengerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerId;
	private Long pnrNo;
	private String passengerName;
	private Character passengerGender;
	private Long passengerAge;
	private Long seatNo;
	private String userName;
	private Long ticketId;

	public Long getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(Long passengerAge) {
		this.passengerAge = passengerAge;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public Long getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(Long pnrNo) {
		this.pnrNo = pnrNo;
	}

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

	public Long getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Long seatNo) {
		this.seatNo = seatNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

}
