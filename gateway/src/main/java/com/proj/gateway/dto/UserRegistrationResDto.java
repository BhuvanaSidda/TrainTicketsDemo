package com.proj.gateway.dto;

public class UserRegistrationResDto {

	private String responseCode;

	private String reponseDesc;

	private String userName;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReponseDesc() {
		return reponseDesc;
	}

	public void setReponseDesc(String reponseDesc) {
		this.reponseDesc = reponseDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
