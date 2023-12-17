package com.pankiba.manytomanyextracolumn.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class EmployeeProjectId implements Serializable {

	private Long employeeId;

	private Long projectId;

	public EmployeeProjectId(Long employeeId, Long projectId) {
		this.employeeId = employeeId;
		this.projectId = projectId;
	}

}
