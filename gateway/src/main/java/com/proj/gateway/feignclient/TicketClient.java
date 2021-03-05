package com.proj.gateway.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.gateway.dto.BookingHistoryResDto;
import com.proj.gateway.dto.TicketReqDto;
import com.proj.gateway.dto.TicketResDto;

@FeignClient(name = "http://ticket-service/ticket/tickets")
public interface TicketClient {

	@PostMapping("/booking")
	public TicketResDto doBooking(@RequestBody TicketReqDto req);

	@GetMapping("/bookingHistory")
	public BookingHistoryResDto getBookingHistory(@RequestParam String userName);

}
