package com.pankiba.onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetomany.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
