package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.many_to_many_mapping.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Trainer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ToString.Include
	String name;
	@ToString.Include
	private String email;
	@ToString.Include
	private int experience;
	@ToString.Include
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdAt; 
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "Trainer_Student",
				joinColumns =  @JoinColumn(name = "Trainer_id"), 
				inverseJoinColumns = @JoinColumn(name = "Student_id"))
	private List<Student> students;
}
