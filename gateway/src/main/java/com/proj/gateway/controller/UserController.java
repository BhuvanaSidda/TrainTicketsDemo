package com.proj.gateway.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.gateway.config.JwtTokenUtil;
import com.proj.gateway.dto.UserRegistrationResDto;
import com.proj.gateway.dto.UserReqDto;
import com.proj.gateway.dto.UserResDto;
import com.proj.gateway.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserServiceImpl userService;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/saveUser")
	public UserRegistrationResDto saveUser(@Valid @RequestBody UserReqDto user) throws JsonProcessingException {
		logger.info(":::user registration:::");
		String password = bcryptEncoder.encode(user.getPassword());
		logger.info("password::" + password);
		user.setPassword(password);
		logger.info("user data:::" + new ObjectMapper().writeValueAsString(user));
		return userService.save(user);
	}

	@GetMapping("/login")
	public UserResDto login(@RequestParam("userName") String userName, @RequestParam("password") String password)
			throws AuthenticationException, JsonProcessingException {
		logger.info("::::::::::token generation::::");
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}catch(Exception e) {
			logger.info("exception:"+e);
		}
		UserResDto user = userService.findOne(userName);
		logger.info("user::" + new ObjectMapper().writeValueAsString(user));
		String token = jwtTokenUtil.generateToken(user);
		logger.info("token::" + token);
		// login response setting
		return settingResponse(user, token);
	}

	private UserResDto settingResponse(UserResDto user, String token) {
		logger.info("User Registration response token setting");
		user.setToken(token);
		logger.info("User Registration response tokem setting done");
		return user;
	}

}
