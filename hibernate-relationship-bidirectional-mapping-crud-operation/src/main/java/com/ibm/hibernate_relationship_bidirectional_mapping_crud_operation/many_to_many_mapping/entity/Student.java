package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ToString.Include
	private String name;
	@ToString.Include
	private String email;
	@ToString.Include
	@UpdateTimestamp
	private LocalDate createdAt;
	
	@ManyToMany(mappedBy = "students")
	List<Trainer> trainers;
}


