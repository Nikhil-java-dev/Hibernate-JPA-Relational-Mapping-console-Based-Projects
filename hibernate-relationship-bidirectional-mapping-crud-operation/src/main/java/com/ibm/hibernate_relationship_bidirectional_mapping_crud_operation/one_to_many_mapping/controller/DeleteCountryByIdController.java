package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao.CountryStateDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.CountryOrStateNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryStateException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_mapping_crud_operation.utility.EntityManagerUtil;


public class DeleteCountryByIdController {
	
	private static final Logger log = LoggerFactory.getLogger(DeleteCountryByIdController.class);
	
	public static void main(String[] args) {
		CountryStateDao dao = new CountryStateDao();
		int countryIdToDelete = 1; // Specify the state ID to delete
		
		try {
			boolean isDeleted = dao.deleteCountryById(countryIdToDelete);
			if(isDeleted) {
				log.info("country with ID {} deleted successfully.", countryIdToDelete);
			} else {
			    log.warn("No country found with ID {}", countryIdToDelete);
			}
		}catch(InvalidCountryStateException e) {
	            log.error("Invalid input data: {}", e.getMessage());
	        } catch(CountryOrStateNotFoundException e) {
	            log.error("Country not found: {}", e.getMessage());
	        } catch(DataPersistenceException e) {
	            log.error("Data persistence error: {}", e.getMessage());
			} catch (Exception e) {
				log.error("An unexpected error occurred: {}", e);
			}
		
		EntityManagerUtil.closeFactory();
		}
}
