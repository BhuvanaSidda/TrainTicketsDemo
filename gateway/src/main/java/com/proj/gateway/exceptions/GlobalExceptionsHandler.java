package com.proj.gateway.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionsHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

	@ExceptionHandler(value = NullPointerException.class)
	public ErrorMessages handleNullPointerException(Exception ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getMessage());
		objError.setErrorOccuredTime(LocalDateTime.now());
		return objError;
	}

	@ExceptionHandler(value = ExpiredJwtException.class)
	public ErrorMessages handleExpiredJwtException(ExpiredJwtException ex) {
		logger.info("In controllerAdvice");
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getMessage());
		objError.setErrorOccuredTime(LocalDateTime.now());
		logger.info("msg:"+objError.getErrorMessage());
		return objError;
	}

	@ExceptionHandler(value = Exception.class)
	public ErrorMessages handleGlobalException(Exception ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getMessage());
		objError.setErrorOccuredTime(LocalDateTime.now());
		return objError;
	}

}
