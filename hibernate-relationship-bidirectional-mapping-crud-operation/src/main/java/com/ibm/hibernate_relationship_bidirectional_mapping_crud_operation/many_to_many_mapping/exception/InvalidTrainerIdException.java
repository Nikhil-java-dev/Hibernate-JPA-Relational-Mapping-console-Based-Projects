package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception;

@SuppressWarnings("serial")
public class InvalidTrainerIdException extends RuntimeException {
	
	public InvalidTrainerIdException() {
		
	}
	
	public InvalidTrainerIdException(String msg) {
		super(msg);
	}
	
	public InvalidTrainerIdException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
