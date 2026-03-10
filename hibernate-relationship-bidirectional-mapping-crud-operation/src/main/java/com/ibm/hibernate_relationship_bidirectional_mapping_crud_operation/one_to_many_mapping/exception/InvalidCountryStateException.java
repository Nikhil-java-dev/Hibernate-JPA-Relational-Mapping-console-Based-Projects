package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception;

@SuppressWarnings("serial")
public class InvalidCountryStateException extends RuntimeException {
	
	public InvalidCountryStateException() {
		
	}
	
	public InvalidCountryStateException(String message) {
		super(message);
	}

	
	public InvalidCountryStateException(String message, Throwable cause) {
		super(message, cause);
	}

}
