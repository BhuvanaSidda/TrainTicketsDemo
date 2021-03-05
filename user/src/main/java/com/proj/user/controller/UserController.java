package com.proj.user.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.user.dto.UserRegistrationResDto;
import com.proj.user.dto.UserReqDto;
import com.proj.user.dto.UserResponseDto;
import com.proj.user.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
@EnableTransactionManagement
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@GetMapping("/getUserByUserName")
	public UserResponseDto getUserByUserName(@RequestParam("username") String username) throws JsonProcessingException {
		logger.info("userController");
		logger.info("UserName::" + username);
		return userService.getUserByUserName(username);
	}

	@PostMapping("/signup")
	public UserRegistrationResDto signup(@Valid @RequestBody UserReqDto userreq) throws JsonProcessingException {
		logger.info("::::::::::::User registration::::::");
		logger.info("Data::::" + new ObjectMapper().writeValueAsString(userreq));
		return userService.save(userreq);
	}

	// zuul methods
	@GetMapping("/userLogin")
	public UserResponseDto doLogin(@RequestParam("username") String username, @RequestParam String password)
			throws JsonProcessingException {
		logger.info("userController");
		logger.info("UserName::" + username);
		UserResponseDto userRes = userService.getUserByUserName(username);
		logger.info("userRes::" + new ObjectMapper().writeValueAsString(userRes));
		if (Objects.nonNull(userRes)) {
			Boolean passwordCheck = bcryptEncoder.matches(password, userRes.getPassword());
			if (passwordCheck == false) {
				userRes.setResponseCode("01");
				userRes.setReponseDesc("Incorrect password");
			}
		}
		return userRes;
	}

	@PostMapping("/userRegistration")
	public UserRegistrationResDto saveUser(@Valid @RequestBody UserReqDto user) throws JsonProcessingException {
		logger.info(":::user registration:::");
		String password = bcryptEncoder.encode(user.getPassword());
		logger.info("password::" + password);
		user.setPassword(password);
		logger.info("user data:::" + new ObjectMapper().writeValueAsString(user));
		return userService.save(user);
	}
	
}
