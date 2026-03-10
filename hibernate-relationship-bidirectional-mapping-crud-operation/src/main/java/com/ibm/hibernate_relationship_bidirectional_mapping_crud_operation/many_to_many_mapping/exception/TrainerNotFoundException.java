package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception;

@SuppressWarnings("serial")
public class TrainerNotFoundException extends RuntimeException {
	public TrainerNotFoundException() {
		
	}
	
	public TrainerNotFoundException(String msg) {
		super(msg);
	}
	
	public TrainerNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
