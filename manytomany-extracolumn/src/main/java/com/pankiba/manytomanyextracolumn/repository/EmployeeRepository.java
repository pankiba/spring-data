package com.pankiba.manytomanyextracolumn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.manytomanyextracolumn.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
