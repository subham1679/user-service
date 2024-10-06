package com.shubham.user.service.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.shubham.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	ApiResponse apiresponse  = new ApiResponse();
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
//		ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
//				.build();
		
		apiresponse.setMessage(message);
		apiresponse.setSuccess("true");
		apiresponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiresponse, HttpStatus.NOT_FOUND);

	}
}
