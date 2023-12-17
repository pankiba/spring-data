package com.pankiba.manytomany.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "projects" })
@ToString
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

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Long salary;

	/**
	 * 
	 * many-to-many database association includes two parent tables which are linked through a third one containing two
	 * Foreign Keys referencing the parent tables.
	 * 
	 * ManyToMany indicates that there is a Many-to-Many relationship between Employee and Project. A employee can have
	 * multiple projects, and a project can have multiple Employees enrolled.
	 *
	 * JoinTable indicates that there is a link table which joins two tables via containing there keys. joinColumns
	 * refers to the column name of owning side(EMPLOYEE_ID of EMPLOYEE), and inverseJoinColumns refers to the column of
	 * inverse side of relationship(PROJECT_ID of PROJECT). Primary key of this joined table is combination of
	 * EMPLOYEE_ID & PROJECT_ID.
	 */
	@JsonManagedReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EMPLOYEE_PROJECT", joinColumns = @JoinColumn(name = "EMPLOYEE_ID"), inverseJoinColumns = @JoinColumn(name = "PROJECT_ID"))
	private Set<Project> projects = new HashSet<Project>();

	public Employee(String firstName, String lastName, String gender, String email, Date birthDate, Date joiningDate,
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

	/*
	 * add/remove utility methods are mandatory if you use bidirectional associations so that you can make sure that
	 * both sides of the association are in sync
	 */
	public void addProject(Project project) {
		projects.add(project);
		project.getEmployees().add(this);
	}

	public void removeProject(Project project) {
		projects.remove(project);
		project.getEmployees().remove(this);
	}

}