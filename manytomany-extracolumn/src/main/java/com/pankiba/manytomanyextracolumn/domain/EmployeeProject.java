package com.pankiba.manytomanyextracolumn.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = { "project" })
@ToString
@NoArgsConstructor
@Entity
public class EmployeeProject implements Serializable {

	private static final long serialVersionUID = -8425108675328193409L;

	@EmbeddedId
	private EmployeeProjectId employeeProjectId;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("employeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("projectId")
	private Project project;

	private LocalDate admissionDate = LocalDate.now();

	public EmployeeProject(Employee employee, Project project) {
		this.employee = employee;
		this.project = project;
		this.employeeProjectId = new EmployeeProjectId(employee.getEmployeeId(), project.getProjectId());
	}

}
