package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.controller;

import java.util.List;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao.TrainerStudentDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Trainer;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;


public class GetAllTrainersController {
	
	private static final Logger log = LoggerUtil.getLogger(GetAllTrainersController.class);
		
	public static void main(String[] args) {
		
		TrainerStudentDao dao = new TrainerStudentDao();
		
		try {
			
			List<Trainer> trainers = dao.getAllTrainers();
			if(!trainers.isEmpty()) {
				for (Trainer trainer : trainers) {
					log.info("trainer: {}", trainer.getEmail());
				}
			} else {
				log.warn("No trainer found in database...🥷‼");
			}
			
		} catch(DataPersistenceException e) {
			log.warn("Unable to fetch trainers", e);
			
		} catch(RuntimeException e) {
			log.error("Unexpected error while fetching trainers details", e);
		}
	}
}
