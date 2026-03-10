package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Adhar;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Person;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.InvalidPersonIdException;



public class GetPersonAdharByPersonIdController {
	private static final Logger log = 	LoggerFactory.getLogger(GetPersonAdharByPersonIdController.class);
	public static void main(String[] args) {
		PersonAdharDao dao = new PersonAdharDao();
		try {
			Person person =  dao.getPersonAdharByPersonId(2);
			if(person != null) {
				log.info("Person Name: {}", person.getName(), person.getEmail());
				Adhar adhar = person.getAdhar();
				if(adhar != null) {
					log.info("Address: {}", adhar.getAddress());
				}else {
					log.warn("Adhar details not found: {}");
				}
				
			}else {
				log.warn("person id not found: {}");
			}
			
		} catch(InvalidPersonIdException e) {
			log.error("Invalid id");
			
		}catch(Exception e) {
            log.error("Unexpected error while fetching person by id: {}", 2, e);
        }
	}
}
