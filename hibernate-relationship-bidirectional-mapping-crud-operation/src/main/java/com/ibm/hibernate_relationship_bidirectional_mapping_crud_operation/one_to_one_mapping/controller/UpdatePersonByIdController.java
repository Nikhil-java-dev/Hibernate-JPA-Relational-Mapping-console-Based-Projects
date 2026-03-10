package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.PersonNotFoundException;

public class UpdatePersonByIdController {
	private static final Logger log = 	LoggerFactory.getLogger(UpdatePersonByIdController.class);
	public static void main(String[] args) {
		int id = 2;
		String name = "Ikka";
        String newEmail = "ikka@gmail.com";
        long newPhone = 9450058842L;
        try {
        	new PersonAdharDao().updatePesonAdhar(name, newEmail, newPhone, id);
            log.info("Person details updated successfully for id: {}", id);
            
		} catch (PersonNotFoundException e) {
			log.warn(e.getMessage());
			
		} catch(DataPersistenceException e) {
			log.warn("Unable to update Students", e);
			
		} catch (Exception e) {
			log.error("Unexpected error while updating person by id: {}", id, e);
		}
	}
}
