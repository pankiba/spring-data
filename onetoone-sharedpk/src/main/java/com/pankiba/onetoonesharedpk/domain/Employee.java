package com.pankiba.onetoonesharedpk.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = { "employeeAddress" })
@NoArgsConstructor
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
	 * To declare a side as not responsible for the relationship, the attribute mappedBy is used. mappedBy refers to the
	 * property name of the association on the owner side. Here, EmployeeDetails entity is owner. mappedBy attribute
	 * declares that it is dependent on owner entity for mapping.
	 *
	 * @OneToOne defines a one-to-one relationship between 2 entities. mappedBy indicates the inverse (non-owning) of
	 *           the relationship.
	 */

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address employeeAddress;

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
