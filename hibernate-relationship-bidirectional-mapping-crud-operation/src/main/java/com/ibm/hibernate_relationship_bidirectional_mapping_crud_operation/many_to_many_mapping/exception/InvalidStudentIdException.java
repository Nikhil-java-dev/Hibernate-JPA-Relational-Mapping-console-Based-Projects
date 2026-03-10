package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception;

@SuppressWarnings("serial")
public class InvalidStudentIdException extends RuntimeException {
	
	public InvalidStudentIdException() {
		
	}
	
	public InvalidStudentIdException(String msg) {
		super(msg);
	}
	
	public InvalidStudentIdException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
