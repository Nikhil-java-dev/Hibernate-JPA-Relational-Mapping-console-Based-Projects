package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao.CountryStateDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.State;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryStateException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_mapping_crud_operation.utility.EntityManagerUtil;



public class InsertStateAndAssociateWithCountryController {
	
	private static final Logger log = LoggerFactory.getLogger(InsertStateAndAssociateWithCountryController.class);
	
	public static void main(String[] args) {
		
		try {
			
			State up = new State();
			up.setStateCode(9);
			up.setName("Uttar Pradesh");
			up.setCapital("Lucknow");
			
			State bihar = new State();
			bihar.setStateCode(10);
			bihar.setName("Bihar");
			bihar.setCapital("Patna");
			
			List<State> states = new ArrayList<>();
			states.add(up);
			states.add(bihar);
			
			new CountryStateDao().saveStateAndAssociateWithCountry(1, states);
			log.info("States associated with country successfully");
			
		} catch (InvalidCountryStateException e) {
		    log.warn("Validation failed: {}", e.getMessage());
		} catch (DataPersistenceException e) {
		    log.error("Database error", e);
		} catch (Exception e) {
            log.error("Unexpected error while associating states with country", e);
        }
		EntityManagerUtil.closeFactory();
	}
}
