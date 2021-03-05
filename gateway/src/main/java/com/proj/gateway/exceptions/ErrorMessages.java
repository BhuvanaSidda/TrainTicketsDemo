package com.proj.gateway.exceptions;

import java.time.LocalDateTime;

public class ErrorMessages {

	private String errorMessage;
	private LocalDateTime errorOccuredTime;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorOccuredTime() {
		return errorOccuredTime;
	}

	public void setErrorOccuredTime(LocalDateTime errorOccuredTime) {
		this.errorOccuredTime = errorOccuredTime;
	}
}

