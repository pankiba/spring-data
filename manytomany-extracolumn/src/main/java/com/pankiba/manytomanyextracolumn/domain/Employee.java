package com.pankiba.manytomanyextracolumn.domain;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "employeeProjects" })
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

	private LocalDate birthDate;

	private LocalDate joiningDate;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Long salary;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EmployeeProject> employeeProjects = new HashSet<EmployeeProject>();

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

	public void addEmployeeProject(Project project) {

		EmployeeProject employeeProject = new EmployeeProject(this, project);
		employeeProjects.add(employeeProject);
		project.getEmployees().add(employeeProject);
	}

	public void removeEmployeeProject(Project project) {

		employeeProjects.removeIf((EmployeeProject employeeProject) -> employeeProject.getEmployee().equals(this)
				&& employeeProject.getProject().equals(project));
	}

}