package com.proj.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proj.user.dto.UserRegistrationResDto;
import com.proj.user.dto.UserReqDto;
import com.proj.user.dto.UserResponseDto;

public interface UserService {

	UserResponseDto getUserByUserName(String username) throws JsonProcessingException;

	UserRegistrationResDto save(UserReqDto userreq) throws JsonProcessingException;
}
