package com.pankiba.onetomany.service;

import java.util.List;

import com.pankiba.onetomany.domain.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);

	public List<Employee> saveEmployees(List<Employee> employees);

	public Employee findEmployeeById(Long employeeId);

	public List<Employee> findAllEmployees();

}
