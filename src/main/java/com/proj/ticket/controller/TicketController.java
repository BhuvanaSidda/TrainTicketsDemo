package com.proj.ticket.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.ticket.dto.BookingHistoryResDto;
import com.proj.ticket.dto.TicketReqDto;
import com.proj.ticket.dto.TicketResDto;
import com.proj.ticket.service.impl.TicketService;

@RestController
@RequestMapping("/tickets")
@Validated
@EnableTransactionManagement
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	TicketService ticketService;

	@PostMapping("/booking")
	public TicketResDto ticketBooking(@Valid @RequestBody TicketReqDto req) {
		logger.info("Booking Controller");
		return ticketService.doBooking(req);
	}

	@GetMapping("/bookingHistory")
	public BookingHistoryResDto BookingHistory(@RequestParam String userName) {
		logger.info("BookingHistory Controller");
		return ticketService.bookingHistory(userName);
	}
	
	
}
