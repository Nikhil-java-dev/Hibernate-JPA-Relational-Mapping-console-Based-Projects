package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidStudentIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.StudentNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;


public class UpdateStudentByIdWithValidationController {
	private static final Logger log = LoggerUtil.getLogger(UpdateStudentByIdWithValidationController.class);
	
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		int studentId = 2;
		String name = "Dukka Golimar";
		String email = "dukkagoli191@gmail.com";
		try {
			
			dao.updateStudentIdWithValidation(studentId, email, name);
			log.info("Students details updated successfully:{} {} {} ", studentId, email,  name);
			
		} catch(InvalidStudentIdException | StudentNotFoundException e) {
			log.warn("Invalid Student details", e);
			
		} catch(DataPersistenceException e) {
			log.warn("DB related error occured", e);
			
		} catch(RuntimeException e) {
			log.error("Unexpected error while updating students details", e);
		}
		
		EntityManagerUtil.closeFactory();
	}
}
