package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException() {
		
	}
	public PersonNotFoundException(String msg) {
		super(msg);
	}
}
