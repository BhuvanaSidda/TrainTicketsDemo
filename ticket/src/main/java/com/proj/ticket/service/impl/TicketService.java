package com.proj.ticket.service.impl;

import com.proj.ticket.dto.BookingHistoryResDto;
import com.proj.ticket.dto.TicketReqDto;
import com.proj.ticket.dto.TicketResDto;

public interface TicketService {

	TicketResDto doBooking(TicketReqDto req);

	BookingHistoryResDto bookingHistory(String userName);

}
