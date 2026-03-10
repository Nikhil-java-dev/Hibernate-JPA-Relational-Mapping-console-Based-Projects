package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Adhar;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Person;



public class InsertPersonAdharController {
	private static final Logger log = LoggerFactory.getLogger(InsertPersonAdharController.class);
	public static void main(String[] args) {
		try {
			
			Adhar adhar = new Adhar();
			
			adhar.setAdharNo(444456686234l);
			adhar.setDob(LocalDate.parse("1993-01-01"));
			adhar.setFName("Delulu");
			adhar.setAddress("puna, India");
			
			Person person = new Person();
			
			person.setName("Selulu");
			person.setEmail("selulu@gmail.in");
			person.setPhone(9094043210l);
			person.setAdhar(adhar);
			
			new PersonAdharDao().savePersonAdharDao(person, adhar);
			log.info("Person and Adhar details inserted successfully");
		} catch (Exception e) {
			log.error("Unexpected error while inserting person and adhar details", e);
			
		}
	}
}
