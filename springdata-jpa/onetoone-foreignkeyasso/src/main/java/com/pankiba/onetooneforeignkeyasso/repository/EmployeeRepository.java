package com.pankiba.onetooneforeignkeyasso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.onetooneforeignkeyasso.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
