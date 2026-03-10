package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao.CountryStateDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.CountryOrStateNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryStateException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_mapping_crud_operation.utility.EntityManagerUtil;


public class DeleteStateByIdWithValidationController {
private static final Logger log = LoggerFactory.getLogger(DeleteStateByIdWithValidationController.class);
	
	public static void main(String[] args) {
		CountryStateDao dao = new CountryStateDao();
		int stateToDelete = 4; // Specify the state ID to delete
		
		try {
			boolean isDeleted = dao.deleteStateByIdWithValidation(stateToDelete);
			if(isDeleted) {
				log.info("State with ID {} deleted successfully.", stateToDelete);
			} else {
			    log.warn("No State found with ID {}", stateToDelete);
			}
		}catch(InvalidCountryStateException e) {
	            log.error("Invalid input data: {}", e.getMessage());
	        } catch(CountryOrStateNotFoundException e) {
	            log.error("Country or State not found: {}", e.getMessage());
	        } catch(DataPersistenceException e) {
	            log.error("Data persistence error: {}", e.getMessage());
			} catch (Exception e) {
				log.error("An unexpected error occurred: {}", e);
			}
		EntityManagerUtil.closeFactory();
		}
}
