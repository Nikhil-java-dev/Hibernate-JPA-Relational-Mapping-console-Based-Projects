package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception;

@SuppressWarnings("serial")
public class InvalidPersonIdException extends RuntimeException {
	public InvalidPersonIdException() {
		
	}
	public InvalidPersonIdException(String message) {
		super(message);
	}
}
