package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception;

@SuppressWarnings("serial")
public class InvalidDateFormateException extends RuntimeException {
	public InvalidDateFormateException(String message) {
		super(message);
	}
	
	public InvalidDateFormateException(String message, Throwable cause) {
		super(message, cause);
	}

}
