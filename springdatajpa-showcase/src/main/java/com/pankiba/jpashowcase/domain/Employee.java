package com.pankiba.jpashowcase.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Long salary;

}
