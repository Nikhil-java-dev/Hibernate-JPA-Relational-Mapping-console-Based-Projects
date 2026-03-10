package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.dao;

import java.util.List;

import org.slf4j.Logger;

import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Student;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity.Trainer;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidStudentIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.InvalidTrainerIdException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.StudentNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.TrainerNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.exception.TrainerStudentNotFoundException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.LoggerUtil;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.exception.DataPersistenceException;
import com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
public class TrainerStudentDao {
	
	private static final Logger log = LoggerUtil.getLogger(TrainerStudentDao.class);
	
	/**
	 * save trainers students
	 * @param trainers
	 * @param students
	 * @return
	 */
	public List<Trainer> saveTrainerStudent(List<Trainer> trainers, List<Student> students) {
		if(trainers == null || trainers.isEmpty() || students == null || students.isEmpty()) {
			throw new TrainerStudentNotFoundException("trainer or student not found!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			
//			for (Student student : students) {
//				em.persist(student);
//			}
//			for(Trainer trainer : trainers) {
//				em.persist(trainer);
//			}
			
			students.forEach(em::persist);
			
			trainers.forEach(em::persist);
			
			et.commit();
			
			return trainers;
			
		} catch(RuntimeException e) {
			log.error("Error while saving trainer or Student");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to delete student due to database error", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * 
	 * @param trainerId
	 * @param students
	 * @return
	 */
	public Trainer saveStudentsAssociatesWithTrainer(int trainerId, List<Student> students) {
		if(trainerId < 1) {
			throw new InvalidTrainerIdException("Id not found!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			Trainer trainer = em.find(Trainer.class, trainerId);
			if(trainer == null) {
				throw new TrainerNotFoundException("Trainer not found!");
			}
			
//			Query query = em.createNativeQuery("INSERT INTO trainerstudent(trainerId, studentId) VALUES (?1, ?2)");
			for (Student student : students) {
				em.persist(student);
//				query.setParameter(1, trainer.getId());
//				query.setParameter(2, student.getId());
//				query.executeUpdate();
				trainer.getStudents().add(student);
			}
			
			et.commit();
			return trainer;
			
		} catch(RuntimeException e) {
			log.error("Error while saving Students");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to delete student due to database error", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean deleteStudentById(int studentId) {
		if(studentId < 1) {
			throw new InvalidStudentIdException("Invalid Student ID!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			Student student = em.find(Student.class, studentId);
			if(student == null) {
				throw new StudentNotFoundException("Student not found!");
			}
			
			em.createNativeQuery("DELETE FROM trainer_student WHERE Student_id = ?1").setParameter(1, studentId).executeUpdate();
			
			em.remove(student);
			et.commit();
			return true;
			
		} catch(RuntimeException e) {
			log.error("Error while deleting Students");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to delete student due to database error", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * delete student without native query
	 * @param studentId
	 * @param trainerId
	 * @return
	 */
	public boolean deleteStudentWithoutNativeQuery(int studentId, int trainerId) {
		if(studentId < 1 || trainerId < 1) {
			throw new TrainerStudentNotFoundException("Invalid trainer Student ID!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			Trainer trainer = em.find(Trainer.class, trainerId);
			Student student = em.find(Student.class, studentId);
			if(trainer == null || student == null) {
				throw new TrainerStudentNotFoundException("Trainer or Student not found!");
			}
			
			trainer.getStudents().size(); // initialize
			boolean removed = trainer.getStudents().remove(student);
			
			if (!removed) {
	            throw new TrainerStudentNotFoundException("Student not linked to this trainer!");
	        }
			
			em.merge(trainer);
			et.commit();
			return true;
			
		} catch(RuntimeException e) {
			log.error("Error while deleting Students");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to remove student from trainer", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean deleteStudentIdWithValidation(int studentId) {
		if(studentId < 1) {
			throw new InvalidStudentIdException("Invalid Student ID!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			Student student = em.find(Student.class, studentId);
			if(student == null) {
				throw new StudentNotFoundException("Student not found!");
			}
			
			String fetchStudentFromtrainerstudent = "SELECT COUNT(*) FROM trainer_student WHERE Student_id = ?1";
			Query query = em.createNativeQuery(fetchStudentFromtrainerstudent);
			query.setParameter(1, studentId);
			Long count = ((Number)query.getSingleResult()).longValue();
			if(count > 0) {
				throw new DataPersistenceException("State is associate with country...‼️ So it cannot be deleted🥷");
			}
			em.remove(student);
			et.commit();
			return true;
			
		} catch(RuntimeException e) {
			log.error("Error while deleting Students");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to delete student due to database error", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public boolean updateStudentIdWithValidation(int studentId, String email, String name) {
		if(studentId < 1) {
			throw new InvalidStudentIdException("Invalid Student ID!");
		}
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction et = null;
		
		try {
			
			et = em.getTransaction();
			et.begin();
			Student student = em.find(Student.class, studentId);
			if(student == null) {
				throw new StudentNotFoundException("Student not found!");
			}
			
			String fetchStudentFromtrainerstudent = "SELECT COUNT(*) FROM trainer_student WHERE Student_id = ?1";
			Query query = em.createNativeQuery(fetchStudentFromtrainerstudent);
			query.setParameter(1, studentId);
			Long count = ((Number)query.getSingleResult()).longValue();
			if(count < 1) {
				throw new DataPersistenceException("Student is not associate with Trainer...‼️ So it cannot be Updated🥷");
			}
			student.setEmail(email);
			student.setName(name);
			et.commit();
			return true;
			
		} catch(RuntimeException e) {
			log.error("Error while updating Students");
			if (et != null && et.isActive()) {
				et.rollback();
			}
			
			throw new DataPersistenceException("Failed to update student due to database error", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}	
		}
	}
	
	/**
	 * Fetch all students from DB.
	 * @return
	 */
	public List<Student> getAllStudents(){

		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			
			String displayAllStudents = "SELECT s FROM Student s";
			TypedQuery<Student> query = em.createQuery(displayAllStudents, Student.class);
			return query.getResultList();
			
		} catch (Exception e) {
			log.error("Error while fetching students", e);
			throw new DataPersistenceException("Unable to fetch Students", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
			
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Trainer> getAllTrainers(){

		EntityManager em = EntityManagerUtil.getEntityManager();
		
		try {
			
			String displayAllTrainers = "SELECT s FROM Trainer s";
			TypedQuery<Trainer> query = em.createQuery(displayAllTrainers, Trainer.class);
			return query.getResultList();
			
		} catch (Exception e) {
			log.error("Error while fetching Trainer", e);
			throw new DataPersistenceException("Unable to fetch Students", e);
			
		} finally {
			if(em != null && em.isOpen()) {
				em.close();
			}
		}
	}
	
}

