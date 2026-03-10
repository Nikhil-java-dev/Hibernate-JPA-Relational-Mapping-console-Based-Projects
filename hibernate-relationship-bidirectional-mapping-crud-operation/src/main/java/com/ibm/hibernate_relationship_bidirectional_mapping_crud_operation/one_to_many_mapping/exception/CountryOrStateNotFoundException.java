package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception;

@SuppressWarnings("serial")
public class CountryOrStateNotFoundException extends RuntimeException {
	public CountryOrStateNotFoundException() {

	}

	public CountryOrStateNotFoundException(String message) {
		super(message);
	}

	public CountryOrStateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
