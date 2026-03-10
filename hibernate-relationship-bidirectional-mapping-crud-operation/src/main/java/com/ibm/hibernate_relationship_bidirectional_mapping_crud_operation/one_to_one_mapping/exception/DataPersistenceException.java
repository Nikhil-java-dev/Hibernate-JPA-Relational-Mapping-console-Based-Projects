package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception;

@SuppressWarnings("serial")
public class DataPersistenceException extends RuntimeException {
	public DataPersistenceException() {

	}

	public DataPersistenceException(String msg) {
		super(msg);
	}
	
	public DataPersistenceException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
