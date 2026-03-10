package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception;

@SuppressWarnings("serial")
public class TrainerStudentNotFoundException extends RuntimeException {
	
	public TrainerStudentNotFoundException() {
		
	}
	
	public TrainerStudentNotFoundException(String msg) {
		super(msg);
	}
	
	public TrainerStudentNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
