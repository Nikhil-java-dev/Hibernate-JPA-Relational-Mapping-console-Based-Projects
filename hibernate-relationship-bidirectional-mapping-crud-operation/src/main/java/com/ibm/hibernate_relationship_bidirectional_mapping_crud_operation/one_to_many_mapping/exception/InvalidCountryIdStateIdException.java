package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception;

@SuppressWarnings("serial")
public class InvalidCountryIdStateIdException extends RuntimeException {
	
	public InvalidCountryIdStateIdException() {
		
	}
	
	public InvalidCountryIdStateIdException(String message) {
		super(message);
	}

	
	public InvalidCountryIdStateIdException(String message, Throwable cause) {
		super(message, cause);
	}

}
