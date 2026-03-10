package com.ibm.hibernate_relationship_bidirectional_mapping_crud_operation.one_to_one_mapping.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"person", "address"})
public class Adhar {
	@Id
	private long adharNo;
	private LocalDate dob;
	private String fName;
	private String address;
	
	@OneToOne(mappedBy = "adhar", fetch = FetchType.LAZY)
	private Person person;
}
