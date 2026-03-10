package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.dao;

import java.time.LocalDate;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Adhar;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity.Person;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.InvalidPersonIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.PersonNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class PersonAdharDao {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-mapping");
	
	/**
	 * Saves a Person along with their Adhar information to the database.
	 * @param person
	 * @param adhar
	 * @return
	 */
	
	public Person savePersonAdharDao(Person person, Adhar adhar) {
		if (person == null || adhar == null) {
			throw new IllegalArgumentException("Person and Adhar must not be null.");
		}
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(adhar);
			em.persist(person);
			et.commit();
			return person;
		} catch(Exception e){
			if(et != null && et.isActive()) {
                et.rollback();
            }
			throw e;
		}finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * Retrieves a Person along with their Adhar information by Person ID.
	 * 
	 * @param personId
	 * @return
	 */
	
	public  Person getPersonAdharByPersonId(int personId) {
		if (personId <= 0) {
			throw new InvalidPersonIdException("Person ID must be greater than zero.");
		}
		EntityManager em = emf.createEntityManager();
		try {
			Person person = em.find(Person.class, personId);
			
			// Force LAZY load
	        if (person != null && person.getAdhar() != null) {
	            person.getAdhar().getAddress();
	        }
	        
			return person;
			
		}finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * Deletes an Adhar record by its Adhar number and disassociates it from the
	 * Person.
	 * 
	 * @param adharNo
	 * @return
	 */
	
	public boolean deleteAdharByAdharNo(long adharNo) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		try {
			et= em.getTransaction();
			et.begin();
			Adhar adhar = em.find(Adhar.class, adharNo);
			if(adhar != null) {
				TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p Where p.adhar.adharNo = :adharNo", Person.class);
				query.setParameter("adharNo", adharNo);
				Person person = (Person) query.getSingleResult();
				if (person != null) {
					person.setAdhar(null);
					em.merge(person);
				}
				em.remove(adhar);
				et.commit();
			}
			return true;
			
		}catch(Exception e) {
			if(et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException();
		}finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	

	/**
	 * Saves a Person along with their Adhar information to the database using
	 * cascade.
	 * 
	 * @param person
	 * @param adhar
	 * @return
	 */
	public Person savePersonAdharUsingCascade(Person person, Adhar adhar) {
		if (person == null || adhar == null) {
			throw new IllegalArgumentException("Person and Adhar must not be null.");
		}
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(person);
			et.commit();
			return person;
		} catch(Exception e){
			if(et != null && et.isActive()) {
                et.rollback();
            }
			throw e;
		}finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * Deletes a Person record by its Person ID.
	 * cascade will delete the associated Adhar record as well.
	 * @param personId
	 * @return
	 */
	public boolean deletePersonByPersonId(int personId) {
		if (personId <= 0) {
			throw new InvalidPersonIdException("Person ID must be greater than zero.");
		}
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		try {
			Person person = em.find(Person.class, personId);
			if (person == null) {
				throw new PersonNotFoundException("Person with ID " + personId + " not found.");
			}
			et = em.getTransaction();
			et.begin();
			em.remove(person);
			et.commit();
			return true;

		} catch (Exception e) {
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw e;
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * Updates a Person's name, email, and phone number by their Person ID.
	 * 
	 * @param name
	 * @param email
	 * @param phone
	 * @param id
	 * @return
	 */
	public boolean updatePesonAdhar(String name, String email, long phone, int id) {
		if(id <= 0 || name == null || name.isEmpty() || email == null || email.isEmpty() || phone <= 0) {
            throw new InvalidPersonIdException("Invalid input parameters for updating Person.");
        }
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		Person person = em.find(Person.class, id);
		if (person == null) {
			throw new PersonNotFoundException("Person with ID " + id + " not found.");
		}
		try {
			et = em.getTransaction();
			et.begin();
			person.setName(name);
			person.setEmail(email);
			person.setPhone(phone);
			Adhar adhar = person.getAdhar();
			if (person.getAdhar() == null) {
				adhar = new Adhar();
				adhar.setAdharNo(123456123456L);
				adhar.setAddress("Noida, sector 19 A");
				adhar.setDob(LocalDate.of(90, 5, 15));
				adhar.setFName("Dinga purohit");
				person.setAdhar(adhar);
			} else {
				adhar.setAddress("Noida, sector 19 A");
				adhar.setFName("Dinga purohit");// Example of updating Adhar number
				adhar.setDob(LocalDate.of(1990, 5, 15));
			}
//			em.merge(person);
			et.commit();
			return true;
		} catch (RuntimeException e) {
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Unable to update Students", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
}
