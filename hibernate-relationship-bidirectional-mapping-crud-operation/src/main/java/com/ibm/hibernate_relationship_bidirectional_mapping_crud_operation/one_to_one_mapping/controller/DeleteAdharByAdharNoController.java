package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao.PersonAdharDao;



public class DeleteAdharByAdharNoController {
	private static final Logger log = 	LoggerFactory.getLogger(DeleteAdharByAdharNoController.class);
	public static void main(String[] args) {
		long adharNo = 211256781234l;
		try {
			boolean isDeleted = new PersonAdharDao().deleteAdharByAdharNo(adharNo);
			if (isDeleted) {
				log.info("Adhar deleted successfully with adhar no: {}", adharNo);
			} else {
				log.warn("Adhar not found with adhar no: {}", adharNo);
			}
		} catch (Exception e) {
			log.error("Unexpected error while deleting adhar by adhar no: {}", adharNo, e);
		}
	}
}
