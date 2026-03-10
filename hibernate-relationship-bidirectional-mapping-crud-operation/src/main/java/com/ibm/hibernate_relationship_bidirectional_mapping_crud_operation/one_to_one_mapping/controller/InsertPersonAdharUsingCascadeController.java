package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Adhar;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Person;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.InvalidDateFormateException;


public class InsertPersonAdharUsingCascadeController {
	private static final Logger log = LoggerFactory.getLogger(InsertPersonAdharUsingCascadeController.class);
	public static void main(String[] args) {
		try {
			Person person = new Person();

			person.setName("Abd");
			person.setEmail("abd@gmail.in");
			person.setPhone(9446543210l);
			Adhar adhar = new Adhar();
			adhar.setAdharNo(211256781234l);
			try {
				adhar.setDob(LocalDate.parse("1988-10-29"));
			} catch (DateTimeParseException e) {
				throw new InvalidDateFormateException("Invalid date format provided for Adhar DOB", e);
			}
			adhar.setFName("Kmeer");
			adhar.setAddress("Karnatak, India");
			
			person.setAdhar(adhar);
			
			new PersonAdharDao().savePersonAdharUsingCascade(person, adhar);
			log.info("Person and Adhar details inserted successfully");
		} catch (InvalidDateFormateException e) {
			log.error("Date format error ", e);
			throw e;
		} catch (Exception e) {
			log.error("Unexpected error while inserting person and adhar details", e);
			throw e;
		}
	}
}
