package com.proj.gateway.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.gateway.dto.UserRegistrationResDto;
import com.proj.gateway.dto.UserReqDto;
import com.proj.gateway.dto.UserResDto;

@FeignClient(name = "http://user-service/user/users")
public interface UserCLient {

	@GetMapping("/getUserByUserName")
	public UserResDto getUserByUserName(@RequestParam String username);

	@PostMapping("/signup")
	public UserRegistrationResDto registration(@RequestBody UserReqDto userReqDto);

}