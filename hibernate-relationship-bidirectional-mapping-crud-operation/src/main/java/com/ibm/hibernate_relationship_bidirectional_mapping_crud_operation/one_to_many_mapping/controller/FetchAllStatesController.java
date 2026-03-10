package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao.CountryStateDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.State;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_mapping_crud_operation.utility.EntityManagerUtil;



public class FetchAllStatesController {

	private static final Logger log = LoggerFactory.getLogger(FetchAllStatesController.class);
	
	public static void main(String[] args) {
		
		CountryStateDao dao = new CountryStateDao();
		try {
			
			List<State> states = dao.getAllStates();
			if(!states.isEmpty()) {
				for (State state : states) {
					log.info("state: {}", state);
				}
			} else {
				log.warn("No states found in database...‼️❎");
			}
			
		} catch(DataPersistenceException e) {
			log.warn("Unable to fetch States", e);
			
		} catch(RuntimeException e) {
			log.error("Unable to fetch States", e);
		}
		EntityManagerUtil.closeFactory();
	}
}
