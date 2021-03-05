package com.proj.user.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.user.dto.UserRegistrationResDto;
import com.proj.user.dto.UserReqDto;
import com.proj.user.dto.UserResponseDto;
import com.proj.user.model.UserDetails;
import com.proj.user.repository.UserRepository;

public class UserServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);

	static UserReqDto userReqDto;
	static UserRegistrationResDto userRegistrationResDto;
	static UserDetails userDetails;
	static UserResponseDto userResponseDto;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setUps() {
		userReqDto = new UserReqDto();
		userReqDto.setUserName("userNameTest");
		userReqDto.setFirstName("fNameTest");
		userReqDto.setLastName("lNameTest");
		userReqDto.setGender("F");
		userReqDto.setMobileNo("9778465123");
		userReqDto.setAge((long) 25);
		userReqDto.setPassword("passsword@123");

		userDetails = new UserDetails();
		userDetails.setUserName("userNameTest");
		userDetails.setFirstName("fNameTest");
		userDetails.setLastName("lNameTest");
		userDetails.setGender("F");
		userDetails.setMobileNo("9778465123");
		userDetails.setAge((long) 25);
		userDetails.setPassword("passsword@123");
	}

	@Test
	@DisplayName("Save UserDetails")
	public void saveUserDetailsTest() throws JsonProcessingException {
		logger.info("Save UserDetails");
		when(userRepository.save(userDetails)).thenReturn(userDetails);
		logger.info("userDetails::" + userDetails);
		userRegistrationResDto = userServiceImpl.save(userReqDto);

		assertEquals("User saved successfully", userRegistrationResDto.getReponseDesc());
	}

	@Test
	@DisplayName("Login Positive Scenario")
	public void loginTest() throws JsonProcessingException {
		when(userRepository.findByUserName("userNameTest")).thenReturn(userDetails);

		userResponseDto = userServiceImpl.getUserByUserName("userNameTest");

		assertEquals("User logged in successfully", userResponseDto.getReponseDesc());
	}

	@Test
	@DisplayName("Login Negative Scenario")
	public void loginTest1() throws JsonProcessingException {
		when(userRepository.findByUserName("userName")).thenReturn(userDetails);

		userResponseDto = userServiceImpl.getUserByUserName("userName");
		userResponseDto.setReponseDesc("User Not found");
		assertEquals("User Not found", userResponseDto.getReponseDesc());
	}

}
