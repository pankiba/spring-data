package com.pankiba.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.manytomany.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
