package com.proj.gateway.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.gateway.dto.UserRegistrationResDto;
import com.proj.gateway.dto.UserReqDto;
import com.proj.gateway.dto.UserResDto;
import com.proj.gateway.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	@Mock
	UserServiceImpl userService;

	@InjectMocks
	UserController userController;

	static UserReqDto userReqDto;
	static UserRegistrationResDto userRegistrationResDto;
	static UserDetails userDetails;
	static UserResDto userResDto;

	@BeforeAll
	public static void setUp() {
		userReqDto = new UserReqDto();
		userReqDto.setUserName("userNameTest");
		userReqDto.setFirstName("fNameTest");
		userReqDto.setLastName("lNameTest");
		userReqDto.setGender("F");
		userReqDto.setMobileNo("9778465123");
		userReqDto.setAge((long) 25);
		userReqDto.setPassword("passsword@123");
	}

	@Test
	@DisplayName("Registration Positive Scenario")
	public void registerTest() throws JsonProcessingException {
		logger.info("Junit: Registration positive Case " + userReqDto.getUserName());
		when(userService.save(userReqDto)).thenReturn(userRegistrationResDto);

		userRegistrationResDto = userController.saveUser(userReqDto);
		logger.info("Junit: Registration positive Case::userRegistrationResDto" + userRegistrationResDto);
		verify(userService).save(userReqDto);
		assertEquals("User saved successfully", userRegistrationResDto.getReponseDesc());
	}

	@Test
	@DisplayName("Registration Negative Scenario")
	public void registerTest1() throws JsonProcessingException {
		logger.info("Junit: Registration Negative Case");
		userReqDto.setUserName("user1");
		when(userService.save(userReqDto)).thenReturn(userRegistrationResDto);

		userRegistrationResDto = userController.saveUser(userReqDto);

		verify(userService).save(userReqDto);
		assertEquals("User already exists", userRegistrationResDto.getReponseDesc());
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Login Positive Scenario") public void loginTest() throws
	 * JsonProcessingException {
	 * when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
	 * 
	 * userResDto = userController.login("user1", "password@123");
	 * 
	 * verify(userService).loadUserByUsername("user1");
	 * assertEquals("User logged in successfully", userResDto.getReponseDesc()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Login Negative Scenario") public void loginTest1() throws
	 * JsonProcessingException {
	 * when(userService.loadUserByUsername("user0")).thenThrow(
	 * InvalidCredentialsException.class);
	 * 
	 * assertThrows(InvalidCredentialsException.class,
	 * ()->userController.login("user0","password@123")); }
	 */
}
