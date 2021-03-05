package com.proj.gateway.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.gateway.dto.BookingHistoryResDto;
import com.proj.gateway.dto.TicketReqDto;
import com.proj.gateway.dto.TicketResDto;
import com.proj.gateway.exceptions.InvalidGrantException;
import com.proj.gateway.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/ticket")
@Validated
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	UserServiceImpl userService;

	@PostMapping("/ticketbooking")
	public TicketResDto ticketBooking(@Valid @RequestBody TicketReqDto req)
			throws JsonProcessingException, InvalidGrantException {
		logger.info("::::::::::TicketController::::::::");
		logger.info("Request::" + new ObjectMapper().writeValueAsString(req));
		return userService.ticketBooking(req);
	}

	@GetMapping("/bookingHistory")
	public BookingHistoryResDto bookingHistory(@RequestParam String userName) {
		return userService.bookingHistory(userName);
	}

}
