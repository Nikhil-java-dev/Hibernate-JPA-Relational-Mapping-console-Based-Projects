package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Student;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidTrainerIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.TrainerNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;



public class InsertStudentAssociationWithTrainer {
private static final Logger log = LoggerUtil.getLogger(InsertStudentAssociationWithTrainer.class);
	
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		try {
			
			Student dukka = new Student();
			dukka.setName("dukka");
			dukka.setEmail("dukka@gmail.com");
			
			Student Shohan = new Student();
			Shohan.setName("Shohan");
			Shohan.setEmail("shohan231@gmail.com");
			
			List<Student> students = Arrays.asList(dukka, Shohan);
			
			if(students.isEmpty()) {
				log.warn("No students provided");
			    return;
			}
			
			dao.saveStudentsAssociatesWithTrainer(2, students);
			log.info("Students details inserted successfully👍");
			
		} catch(InvalidTrainerIdException | TrainerNotFoundException e) {
			log.warn("Invalid trainer details", e);
			
		} catch(DataPersistenceException e) {
			log.warn("DB related error occured", e);
			
		} catch(RuntimeException e) {
			log.error("Unexpected error while inserting trainers and students details", e);
		}
		
		EntityManagerUtil.closeFactory();
	}
}
