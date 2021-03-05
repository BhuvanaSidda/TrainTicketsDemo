package com.proj.ticket.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.ticket.dto.BookingDetailsDto;
import com.proj.ticket.dto.BookingHistoryResDto;
import com.proj.ticket.dto.PassengerDetailsDto;
import com.proj.ticket.dto.Response;
import com.proj.ticket.dto.TicketReqDto;
import com.proj.ticket.dto.TicketResDto;
import com.proj.ticket.model.PassengerDetails;
import com.proj.ticket.model.TicketDetails;
import com.proj.ticket.repository.PassengerDetailsRepository;
import com.proj.ticket.repository.TicketDetailsRepository;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDetailsRepository ticketDetailsRepository;

	@Autowired
	PassengerDetailsRepository passengerDetailsRepository;

	@Override
	public TicketResDto doBooking(TicketReqDto req) {
		// Ticket details saving
		TicketDetails ticketDetails = saveTicketDetails(req);
		ticketDetailsRepository.save(ticketDetails);
		// Passenger details saving
		savePassengerDetails(req, ticketDetails.getTicketId());
		// response setting
		TicketResDto ticketResDto = settingResponse(req, ticketDetails.getTicketId());
		return ticketResDto;
	}

	private TicketResDto settingResponse(TicketReqDto req, Long ticketId) {
		TicketResDto res = new TicketResDto();
		res.setTicketNo(ticketId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(req.getDepartureDate(), formatter);
		String strDate = formatter.format(dateTime);
		res.setDepartureDate(strDate);
		res.setSource(req.getSource());
		res.setDestination(req.getDestination());
		res.setNoOfPassengers(req.getNoOfPassengers());
		res.setTrainNo(req.getTrainNo());
		res.setUserName(req.getUserName());
		Response response = new Response();
		response.setResponseCode("00");
		response.setReponseDesc("Ticket booking completed Successfully");
		res.setRes(response);
		return res;
	}

	private TicketDetails saveTicketDetails(TicketReqDto req) {
		TicketDetails ticketDetails = new TicketDetails();
		BeanUtils.copyProperties(req, ticketDetails);
		ticketDetails.setTicketId(System.currentTimeMillis());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(req.getDepartureDate(), formatter);
		ticketDetails.setDepartureDate(dateTime);
		ticketDetails.setCreatedDate(LocalDateTime.now());
		return ticketDetails;
	}

	private void savePassengerDetails(TicketReqDto req, Long ticketId) {

		for (PassengerDetailsDto obj : req.getPassgerDetails()) {
			PassengerDetails passengerDetails = new PassengerDetails();
			passengerDetails.setPassengerName(obj.getPassengerName());
			passengerDetails.setPassengerGender(obj.getPassengerGender());
			passengerDetails.setPassengerAge(obj.getAge());
			passengerDetails.setPnrNo(System.currentTimeMillis());
			passengerDetails.setTicketId(ticketId);
			passengerDetails.setUserName(req.getUserName());
			passengerDetailsRepository.save(passengerDetails);
		}
	}

	@Override
	public BookingHistoryResDto bookingHistory(String userName) {
		List<TicketDetails> li = ticketDetailsRepository.findByUserName(userName);
		BookingHistoryResDto bookingres = new BookingHistoryResDto();
		List<BookingDetailsDto> liRes = new ArrayList<>();
		if(li.isEmpty()) {
			bookingres.setReponseDesc("No transactions found");
			bookingres.setResponseCode("01");
			return bookingres;
		}
		for (TicketDetails obj : li) {
			BookingDetailsDto ticketRes = new BookingDetailsDto();
			ticketRes.setTicketNo(obj.getTicketId());
			ticketRes.setSource(obj.getSource());
			ticketRes.setDestination(obj.getDestination());
			ticketRes.setTrainNo(obj.getTrainNo());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String strDate = formatter.format(obj.getDepartureDate());
			ticketRes.setDepartureDate(strDate);
			ticketRes.setNoOfPassengers(obj.getNoOfPassengers());
			liRes.add(ticketRes);
		}
		bookingres.setReponseDesc("Success");
		bookingres.setResponseCode("00");
		bookingres.setUserName(userName);
		bookingres.setBookings(liRes);
		return bookingres;
	}

}
