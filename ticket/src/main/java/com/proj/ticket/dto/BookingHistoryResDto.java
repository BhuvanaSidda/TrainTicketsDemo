package com.proj.ticket.dto;

import java.util.List;

public class BookingHistoryResDto {

	private String responseCode;
	private String reponseDesc;
	private String userName;
	private List<BookingDetailsDto> bookings;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReponseDesc() {
		return reponseDesc;
	}

	public void setReponseDesc(String reponseDesc) {
		this.reponseDesc = reponseDesc;
	}


	public List<BookingDetailsDto> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDetailsDto> bookings) {
		this.bookings = bookings;
	}

}
