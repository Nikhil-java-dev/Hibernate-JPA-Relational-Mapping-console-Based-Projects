package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_many_mapping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(onlyExplicitlyIncluded = true)
public class State {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ToString.Include
	private int stateCode;
	@ToString.Include
	private String name;
	@ToString.Include
	private String capital;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
}
