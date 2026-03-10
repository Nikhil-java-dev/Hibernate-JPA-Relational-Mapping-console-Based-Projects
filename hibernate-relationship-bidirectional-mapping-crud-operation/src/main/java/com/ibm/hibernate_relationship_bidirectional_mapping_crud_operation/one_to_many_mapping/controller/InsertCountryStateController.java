package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao.CountryStateDao;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.Country;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.State;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryStateException;
import com.ibm.hibernate_relationship_mapping_crud_operation.utility.EntityManagerUtil;



public class InsertCountryStateController {
	private static final Logger log = LoggerFactory.getLogger(InsertCountryStateController.class);
	public static void main(String[] args) {
		
		CountryStateDao dao = new CountryStateDao();
		try {
			
			Country india = new Country();
			Country england = new Country();
			
			State karnataka = new State();
			karnataka.setStateCode(29);
			karnataka.setName("Karnataka");
			karnataka.setCapital("Bangalore");
			karnataka.setCountry(india);
			
			State tamilNadu = new State();
			tamilNadu.setStateCode(33);
			tamilNadu.setName("Tamil Nadu");
			tamilNadu.setCapital("Chennai");
			tamilNadu.setCountry(india);
			
			
			State london = new State();
			london.setStateCode(20);
			london.setName("London");
			london.setCapital("London");
			london.setCountry(england);
			
			State oval = new State();
			oval.setStateCode(21);
			oval.setName("oval");
			oval.setCapital("oval");
			oval.setCountry(england);
			
			
			List<State> states = new ArrayList<>();
			states.add(karnataka);
			states.add(tamilNadu);
			
			
			india.setCountryCode(91);
			india.setName("India");
			india.setCapital("New Delhi");
			india.setStates(states);
			
			List<State> states1 = Arrays.asList(london, oval);
			
			england.setCountryCode(44);
			england.setName("England");
			england.setCapital("London");
			england.setStates(states);
			
			dao.saveCountryState(india, states);
			dao.saveCountryState(england, states1);
			
			log.info("Country and States details inserted successfully");
		} catch (InvalidCountryStateException icse) {
			log.error("Invalid country or state details: {}", icse.getMessage());
			
		} catch (Exception e) {
			log.error("Unexpected error while inserting country and states details", e);
			
		}
		EntityManagerUtil.closeFactory();
	}
}
