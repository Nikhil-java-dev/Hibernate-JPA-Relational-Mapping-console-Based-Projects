package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;

import java.util.List;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Student;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;


public class GetAllStudentsController {
	
	private static final Logger log = LoggerUtil.getLogger(GetAllStudentsController.class);
		
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		
		try {
			
			List<Student> students = dao.getAllStudents();
			if(!students.isEmpty()) {
				for (Student student : students) {
					log.info("student: {}", student);
				}
			} else {
				log.warn("No student found in database...🥷‼");
			}
			
		} catch(DataPersistenceException e) {
			log.warn("Unable to fetch Students", e);
			
		} catch(RuntimeException e) {
			log.error("Unexpected error while fetching students details", e);
		}
	}
}
