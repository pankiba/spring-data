package com.pankiba.onetoonesharedpk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetoonesharedpk.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
