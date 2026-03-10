package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	private LoggerUtil() {
		
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
		
	}
}
