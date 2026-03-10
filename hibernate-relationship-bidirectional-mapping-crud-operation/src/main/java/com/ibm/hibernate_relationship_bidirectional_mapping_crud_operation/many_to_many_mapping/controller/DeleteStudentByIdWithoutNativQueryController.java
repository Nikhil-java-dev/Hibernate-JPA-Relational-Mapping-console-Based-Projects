package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidStudentIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidTrainerIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.TrainerStudentNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;


public class DeleteStudentByIdWithoutNativQueryController {
	private static final Logger log = LoggerUtil.getLogger(DeleteStudentByIdWithoutNativQueryController.class);
	
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		int studentId = 3;
		int trainerId = 2;
		
		try {
			
			dao.deleteStudentWithoutNativeQuery(studentId, trainerId);
			log.info("Students details deleted successfully");
			
		} catch(InvalidTrainerIdException e) {
			log.warn("Invalid Trainer Id", e);
			
		}catch(InvalidStudentIdException e) {
			log.warn("Invalid Student Id", e);
			
		}catch(TrainerStudentNotFoundException e) {
			log.warn("Invalid Trainer or Student details", e);
			
		} catch(DataPersistenceException e) {
			log.warn("DB related error occured", e);
			
		} catch(RuntimeException e) {
			log.error("Unexpected error while deleting trainer-student relation", e);

		}
		
		EntityManagerUtil.closeFactory();
	}
}
