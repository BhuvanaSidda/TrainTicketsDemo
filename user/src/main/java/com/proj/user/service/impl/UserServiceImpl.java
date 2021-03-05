package com.proj.user.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.user.dto.UserRegistrationResDto;
import com.proj.user.dto.UserReqDto;
import com.proj.user.dto.UserResponseDto;
import com.proj.user.model.UserDetails;
import com.proj.user.repository.UserRepository;
import com.proj.user.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserResponseDto getUserByUserName(String username) throws JsonProcessingException {
		UserDetails user = userRepository.findByUserName(username);
		logger.info("userDetails:::" + new ObjectMapper().writeValueAsString(user));
		return settingUserResponseDto(user);
	}

	@Override
	public UserRegistrationResDto save(UserReqDto userReq) throws JsonProcessingException {
		logger.info("User registration service page");
		UserDetails objuser = userRepository.findByUserName(userReq.getUserName());
		UserRegistrationResDto res = new UserRegistrationResDto();
		res.setUserName(userReq.getUserName());
		if (Objects.nonNull(objuser)) {
			res.setReponseDesc("User already exists");
			res.setResponseCode("01");
			return res;
		}
		// setting userreq to user model
		UserDetails user = new UserDetails();
		BeanUtils.copyProperties(userReq, user);
		try {
			userRepository.save(user);
			res.setReponseDesc("User saved successfully");
			res.setResponseCode("00");
		} catch (Exception e) {
			res.setReponseDesc("Unable to save user");
			res.setResponseCode("01");
		}

		return res;
	}

	private UserResponseDto settingUserResponseDto(UserDetails user) throws JsonProcessingException {
		logger.info("setting userResponseDto");
		UserResponseDto userResponseDto = new UserResponseDto();
		if (Objects.nonNull(user)) {
			userResponseDto.setResponseCode("00");
			userResponseDto.setReponseDesc("User logged in successfully");
			userResponseDto.setUserName(user.getUserName());
			userResponseDto.setFirstName(user.getFirstName());
			userResponseDto.setLastName(user.getLastName());
			userResponseDto.setGender(user.getGender());
			userResponseDto.setAge(user.getAge());
			userResponseDto.setMobileNo(user.getMobileNo());
			userResponseDto.setPassword(user.getPassword());
		} else {
			userResponseDto.setResponseCode("01");
			userResponseDto.setReponseDesc("User Not found");
		}
		logger.info("response::" + new ObjectMapper().writeValueAsString(userResponseDto));
		return userResponseDto;
	}

}
