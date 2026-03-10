package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.InvalidPersonIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.PersonNotFoundException;

public class DeletePersonByPersonIdController {
	private static final Logger log = LoggerFactory.getLogger(DeletePersonByPersonIdController.class);
	public static void main(String[] args) {
		int id = 1;
		PersonAdharDao personAdharDao = new PersonAdharDao();
		
		try {
			personAdharDao.deletePersonByPersonId(id);
			log.info("Person deleted successfully for id: {}", id);
			
		}catch(InvalidPersonIdException e) {
            log.warn("Person not found for id: {}", e.getMessage());
            
        } catch(PersonNotFoundException e) {
            log.warn(e.getMessage());
            
        } catch(DataPersistenceException e) {
        	log.warn("Unable to update Students", e);
        	
        } catch (Exception e) {
            log.error("Unexpected error while deleting person by id: {}", id, e);
        }
	}
}
