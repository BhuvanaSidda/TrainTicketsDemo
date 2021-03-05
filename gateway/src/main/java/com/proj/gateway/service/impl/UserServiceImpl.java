package com.proj.gateway.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.gateway.dto.BookingHistoryResDto;
import com.proj.gateway.dto.Response;
import com.proj.gateway.dto.TicketReqDto;
import com.proj.gateway.dto.TicketResDto;
import com.proj.gateway.dto.UserRegistrationResDto;
import com.proj.gateway.dto.UserReqDto;
import com.proj.gateway.dto.UserResDto;
import com.proj.gateway.feignclient.TicketClient;
import com.proj.gateway.feignclient.UserCLient;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserCLient userCLient;

	@Autowired
	TicketClient ticketClient;

	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username :::" + username);
		UserResDto user = userCLient.getUserByUserName(username);
		try {
			logger.info("user data in gateway:::" + new ObjectMapper().writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	public UserResDto findOne(String username) {
		logger.info("userName::" + username);
		return userCLient.getUserByUserName(username);
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public UserRegistrationResDto save(UserReqDto user) {
		logger.info("Registration service page::");
		String password = bcryptEncoder.encode(user.getPassword());
		logger.info("password::" + password);
		user.setPassword(password);
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("User Registration");
		return circuitBreaker.run(() -> userCLient.registration(user), throwable -> getDefaultMsgUserSave(user));
	}

	public TicketResDto ticketBooking(TicketReqDto req) {
		logger.info("Ticket booking service");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("Ticket Booking");
		return circuitBreaker.run(() -> ticketClient.doBooking(req), throwable -> getDefaultMsgTicketBooking(req));
	}

	public BookingHistoryResDto bookingHistory(String userName) {
		logger.info("User ticket history service");
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("User ticket history");
		return circuitBreaker.run(() -> ticketClient.getBookingHistory(userName),
				throwable -> getDefaultMsgTicketHistory(userName));
	}

	// User registration: User Service unable
	public UserRegistrationResDto getDefaultMsgUserSave(UserReqDto user) {
		UserRegistrationResDto res = new UserRegistrationResDto();
		res.setReponseDesc("Please try after sometime");
		res.setResponseCode("01");
		return res;
	}

	// Ticket Booking: Ticket Service unable
	public TicketResDto getDefaultMsgTicketBooking(TicketReqDto req) {
		TicketResDto ticketRes = new TicketResDto();
		Response res = new Response();
		res.setReponseDesc("Please try after sometime");
		res.setResponseCode("01");
		ticketRes.setRes(res);
		return ticketRes;
	}

	// Ticket history: Ticket Service unable
	public BookingHistoryResDto getDefaultMsgTicketHistory(String userName) {
		BookingHistoryResDto res = new BookingHistoryResDto();
		res.setReponseDesc("Please try after sometime");
		res.setResponseCode("01");
		return res;
	}

}
