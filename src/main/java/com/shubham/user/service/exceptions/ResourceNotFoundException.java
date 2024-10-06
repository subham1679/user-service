package com.shubham.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("some resorce not found on server");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
