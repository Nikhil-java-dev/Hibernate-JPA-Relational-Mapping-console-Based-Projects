package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Student;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Trainer;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.TrainerStudentNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;


public class InsertTrainerStudentController {
	
	private static final Logger log = LoggerUtil.getLogger(InsertTrainerStudentController.class);
	
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		try {
			
			Student ikka = new Student();
			ikka.setName("Ikka");
			ikka.setEmail("ikka@gmail.com");
			
			Student dukka = new Student();
			dukka.setName("dukka");
			dukka.setEmail("dukka@gmail.com");
			
			List<Student> students = Arrays.asList(ikka, dukka);
			
			Trainer guruji = new Trainer();
			guruji.setName("Guruji");
			guruji.setEmail("guruji@gmail.com");
			guruji.setExperience(10);
			guruji.setStudents(students);
			
			Trainer master = new Trainer();
			master.setName("master");
			master.setEmail("master@gmail.com");
			master.setExperience(6);
			master.setStudents(students);
			
			List<Trainer> trainers = Arrays.asList(guruji, master);
			
			if(trainers.isEmpty() || students.isEmpty()) {
				log.warn("No trainers or students provided");
			    return;
			}
			
			dao.saveTrainerStudent(trainers, students);
			log.info("Trainers and Students details inserted successfully");
			
		}catch(TrainerStudentNotFoundException e) {
			log.warn("Invalid trainer or student details", e);
		} catch(DataPersistenceException e) {
			log.warn("DB related error occured", e);
		} catch(RuntimeException e) {
			log.error("Unexpected error while inserting trainers and students details", e);
		}
		
		EntityManagerUtil.closeFactory();
	}
}
