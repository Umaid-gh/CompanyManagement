package com.umaid.demoquickstart.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.umaid.demoquickstart.exception.domain.ApiErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResponseStatusException.class)
	public ResponseEntity<ApiErrorResponse> handleStatusException(HttpServletRequest request, ResponseStatusException responseException){
		
		ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
		apiErrorResponse.setStatus(responseException.getStatus().value());
		apiErrorResponse.setMessage(responseException.getReason());
		
		return ResponseEntity.status(responseException.getStatus()).body(apiErrorResponse);
	}

}
