package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception;

@SuppressWarnings("serial")
public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException() {
		
	}
	
	public StudentNotFoundException(String msg) {
		super(msg);
	}
	
	public StudentNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
