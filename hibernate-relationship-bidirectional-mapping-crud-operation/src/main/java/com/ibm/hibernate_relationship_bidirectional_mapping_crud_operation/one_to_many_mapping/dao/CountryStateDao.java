package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.Country;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity.State;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.CountryOrStateNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryIdStateIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.exception.InvalidCountryStateException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class CountryStateDao {
	
	private static final Logger log = LoggerFactory.getLogger(CountryStateDao.class);
	

	/**
	 * Saves country along with its states.
	 * 
	 * @param country
	 * @param states
	 * @return
	 */
	public Country saveCountryState(Country country, List<State> states) {

		if (country == null || states == null) {
			throw new InvalidCountryStateException("Country or State details cannot be null...🥷");
		}

		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			et = em.getTransaction();
			et.begin();
			
			for (State state : states) {
				state.setCountry(country);
			}
			country.setStates(states);
			em.persist(country);
			et.commit();
			return country;
			
		} catch (Exception e) {
			log.error("Error while saving Country or States", e);
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Could not save...!!! May be id not valid!");

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}

	/**
	 * Saves states and associates them with an existing country.
	 * 
	 * @param countryId
	 * @param states
	 * @return
	 */
	public Country saveStateAndAssociateWithCountry(int countryId, List<State> states) {
		if (countryId <= 0 || states == null || states.isEmpty()) {
			throw new InvalidCountryStateException("Invalid input data...❎‼️❗");
		}
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			Country country = em.find(Country.class, countryId);
			if (country == null) {
				throw new CountryOrStateNotFoundException("Country not found");
			}
			et = em.getTransaction();
			et.begin();
			for (State state : states) {
				state.setCountry(country);
				em.persist(state);
			}
			et.commit();
			return country;

		} catch (Exception e) {
			log.error("Error while saving statesId");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Failed to save states" + e.getMessage(), e);

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	/**
	 * Deletes state by ID.
	 * 
	 * @param stateId
	 * @return
	 */
	public boolean deleteStateById(int stateId) {

		if (stateId <= 0) {
			throw new InvalidCountryStateException("Invalid State ID...❎‼️❗");
		}
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			et = em.getTransaction();
			et.begin();
			State state = em.find(State.class, stateId);
			if (state == null) {
				throw new CountryOrStateNotFoundException("State not found...❎🕵️‍♀️");
			}
			em.remove(state);
			et.commit();
			return true;

		} catch (Exception e) {
			log.error("Error while deleting statesId", e);
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Failed to delete state: " + e.getMessage(), e);

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
	/**
	 * 
	 * @param countryId
	 * @return
	 */
	public boolean deleteCountryById(int countryId) {
		if (countryId <= 0) {
			throw new InvalidCountryIdStateIdException("Invalid countryId ...❎‼️❗");
		}
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			et = em.getTransaction();
			et.begin();
			Country country = em.find(Country.class, countryId);
			if (country == null) {
				throw new CountryOrStateNotFoundException("State not found...❎🕵️‍♀️");
			}
			em.remove(country);
			et.commit();
			return true;

		} catch (Exception e) {
			log.error("Error while deleting statesId", e);
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Failed to delete state: " + e.getMessage(), e);

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
	
	/**
	 * Deletes state by ID with validation.
	 * 
	 * @param stateId
	 * @return
	 */
	public boolean deleteStateByIdWithValidation(int stateId) {

		if (stateId <= 0) {
			throw new InvalidCountryStateException("Invalid State ID...❎‼️❗");
		}

		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			State state = em.find(State.class, stateId);
			if (state == null) {
				throw new CountryOrStateNotFoundException("State not found...❎🕵️‍♀️");
			}
			String fetchState = "SELECT COUNT(*) FROM state WHERE id = ?1";
			Query query = em.createNativeQuery(fetchState);
			query.setParameter(1, stateId);
			Long count = ((Number) query.getSingleResult()).longValue();
			if (count > 0) {
				throw new DataPersistenceException("State is associate with country...‼️ So it cannot be deleted🥷");
			}
			et = em.getTransaction();
			et.begin();
			em.remove(state);
			et.commit();
			return true;

		} catch (Exception e) {
			log.error("Error while deleting states", e);
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Could not delete...❗May be id not valid!!", e);

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}

	/**
	 * Updates state by ID with validation.
	 * 
	 * @param stateId
	 * @return
	 */
	public boolean updateStateByIdWithValidation(int stateId, String name, String capital) {

		if (stateId <= 0) {
			throw new InvalidCountryStateException("Invalid State ID...❎‼️❗");
		}

		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;

		try {

			State state = em.find(State.class, stateId);
			if (state == null) {
				throw new CountryOrStateNotFoundException("State not found...❎🕵️‍♀️");
			}
			String fetchCountryStateId = "SELECT COUNT(*) FROM state WHERE id = :id";
			Query query = em.createNativeQuery(fetchCountryStateId);
			query.setParameter("id", stateId);
			Long count = ((Number) query.getSingleResult()).longValue();
			if (count < 1) {
				throw new DataPersistenceException(
						"State is not associate with country...‼️ So it could not update 🥷");
			}

			state.setName(name);
			state.setCapital(capital);

			et = em.getTransaction();
			et.begin();
			em.merge(state);
			et.commit();
			return true;

		} catch (DataPersistenceException e) {
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw e;

		} catch (Exception e) {
			log.error("Error while fetching states", e);
			if (et != null && et.isActive()) {
				et.rollback();
			}
			throw new DataPersistenceException("Could not update...❗May be id not valid!!", e);

		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
	/**
	 * Fetching all states.
	 * 
	 * @return
	 */
	public List<State> getAllStates() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			String displayAllState = "SELECT s FROM State s";
			TypedQuery<State> query = em.createQuery(displayAllState, State.class);
			return query.getResultList();
		} catch (Exception e) {
			log.error("Error while fetching states", e);
			throw new DataPersistenceException("Unable to fetch States", e);
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
			
		}

	}
}
