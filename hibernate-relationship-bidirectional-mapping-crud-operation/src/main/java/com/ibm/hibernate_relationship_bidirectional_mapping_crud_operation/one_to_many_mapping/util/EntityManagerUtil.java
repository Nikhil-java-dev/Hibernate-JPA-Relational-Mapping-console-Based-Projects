package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util;

import org.slf4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {
	
	private static final Logger log = LoggerUtil.getLogger(EntityManagerUtil.class);
	private static final EntityManagerFactory emf;
	static {
		try {
			emf = Persistence.createEntityManagerFactory("hibernate-mapping");
		} catch(Exception e) {
			log.error("EMF creation is failed!");
			throw new ExceptionInInitializerError(e);
		}
	}
	
	private EntityManagerUtil() {
		
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
		
	}
	
	public static void closeFactory() {
		if(emf != null && emf.isOpen()) {
			emf.close();
			log.info("EntityManagerFactory closed!");
		}
	}

}
