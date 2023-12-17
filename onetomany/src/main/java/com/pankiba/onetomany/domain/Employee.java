package com.pankiba.onetomany.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = { "businessUnit" })
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 6798516679232905689L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeid_generator")
	@SequenceGenerator(name = "employeeid_generator", sequenceName = "emp_id_seq", allocationSize = 50, initialValue = 5001)
	private Long employeeId;

	private String firstName;

	private String lastName;

	private String gender;

	@Column(unique = true)
	private String email;

	private LocalDate birthDate;

	private LocalDate joiningDate;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Long salary;

	/**
	 * JoinColumn says that Employee table will contain a separate column BUSINESS_UNIT_ID which will act as a foreign
	 * key reference to primary key of BUSINESS_UNIT table.
	 *
	 * ManyToOne annotation is used to define a single value association with another entity bean.
	 *
	 * Many to one side is the owning side of the relationship. Owning side of the relation tracked by Hibernate is the
	 * side of the relation that owns the foreign key in the database.
	 */
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "BUSINESS_UNIT_ID")
	private BusinessUnit businessUnit;

	public Employee() {
	}

	public Employee(String firstName, String lastName, String gender, String email, LocalDate birthDate, LocalDate joiningDate,
			Grade grade, Long salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.birthDate = birthDate;
		this.joiningDate = joiningDate;
		this.grade = grade;
		this.salary = salary;
	}

}
