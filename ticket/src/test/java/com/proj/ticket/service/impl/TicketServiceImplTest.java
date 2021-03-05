package com.proj.ticket.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.ticket.dto.BookingHistoryResDto;
import com.proj.ticket.dto.PassengerDetailsDto;
import com.proj.ticket.dto.TicketReqDto;
import com.proj.ticket.dto.TicketResDto;
import com.proj.ticket.model.PassengerDetails;
import com.proj.ticket.model.TicketDetails;
import com.proj.ticket.repository.TicketDetailsRepository;

public class TicketServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImplTest.class);

	static TicketDetails ticketDetails;
	static TicketReqDto ticketReqDto;
	static TicketResDto ticketResDto;
	static BookingHistoryResDto bookingHistoryResDto;
	static List<TicketDetails> liTicketDetails;
	static PassengerDetails passengerDetails;
	static PassengerDetailsDto passengerDetailsDto;
	static List<PassengerDetailsDto> liPassengerDetailsDto = new ArrayList<>();
	static List<PassengerDetails> lipassengerDetails = new ArrayList<>();
	List<PassengerDetailsDto> spy = Mockito.spy(liPassengerDetailsDto);
	List<PassengerDetails> spy1 = Mockito.spy(lipassengerDetails);
	
	@Mock
	TicketDetailsRepository ticketRepository;

	@InjectMocks
	TicketServiceImpl ticketServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setUps() {
		ticketReqDto = new TicketReqDto();

		passengerDetailsDto = new PassengerDetailsDto();
		passengerDetailsDto.setPassengerName("Bhuvana");
		passengerDetailsDto.setAge((long) 25);
		passengerDetailsDto.setPassengerGender('F');
		liPassengerDetailsDto.add(passengerDetailsDto);
		
		passengerDetails = new PassengerDetails();
		passengerDetails.setUserName("userTest");
		passengerDetails.setPassengerAge((long) 20);
		passengerDetails.setPassengerGender('F');
		passengerDetails.setPassengerId(System.currentTimeMillis());
		passengerDetails.setPassengerName("Bhuvana");
		passengerDetails.setPnrNo((long) 1);
		passengerDetails.setSeatNo((long) 2);
		passengerDetails.setTicketId((long) 1);
		lipassengerDetails.add(passengerDetails);

		ticketReqDto.setUserName("userTest");
		ticketReqDto.setTrainNo((long) 23323);
		ticketReqDto.setSource("HYD");
		ticketReqDto.setDestination("RJY");
		ticketReqDto.setDepartureDate("2021-03-20 21:00:00");
		ticketReqDto.setNoOfPassengers((long) 2);
		ticketReqDto.setPassgerDetails(liPassengerDetailsDto);

		ticketDetails = new TicketDetails();

		ticketDetails.setUserName("userTest");
		ticketDetails.setTrainNo((long) 23323);
		ticketDetails.setSource("HYD");
		ticketDetails.setDestination("RJY");
		ticketDetails.setNoOfPassengers((long) 2);

	}

	@Test
	@DisplayName("Book train tickets")
	public void saveUserDetailsTest() throws JsonProcessingException {
		logger.info("Save UserDetails");
		when(ticketRepository.save(ticketDetails)).thenReturn(ticketDetails);
		logger.info("userDetails::" + ticketDetails);
		ticketResDto = ticketServiceImpl.doBooking(ticketReqDto);

		assertEquals("Ticket booking completed Successfully", ticketResDto.getRes().getReponseDesc());
	}

	@Test
	@DisplayName("Tickets history")
	public void loginTest() throws JsonProcessingException {
		when(ticketRepository.findByUserName("userTest")).thenReturn(liTicketDetails);

		bookingHistoryResDto = ticketServiceImpl.bookingHistory("userTest");

		assertEquals("Success", bookingHistoryResDto.getReponseDesc());
	}

}
