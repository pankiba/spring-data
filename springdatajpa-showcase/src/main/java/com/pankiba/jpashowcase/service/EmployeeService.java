package com.pankiba.jpashowcase.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pankiba.jpashowcase.domain.Employee;

public interface EmployeeService {

	// Basic CRUD operations

	public Employee saveEmployee(Employee employee);

	public List<Employee> saveEmployees(List<Employee> employees);

	public Employee findEmployee(Long employeeId);

	public List<Employee> findEmployees();

	public Employee updateEmployee(Employee employee);

	public List<Employee> updateEmployees(List<Employee> employees);

	public void deleteEmployee(Employee employee);

	public void deleteAllEmployees();

	// Spring Data out of the box features for building dynamic queries

	public List<Employee> findAllEmployeesJpql();

	public List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

	public List<Employee> findByFirstNameAndLastNameIndexJpql(String firstName, String lastName);

	public List<Employee> findByFirstNameAndLastNameIndexNative(String firstName, String lastName);

	public List<Employee> findByFirstNameAndLastNameNamedJpql(String firstName, String lastName);

	public List<Employee> findByFirstNameAndLastNameNamedNative(String firstName, String lastName);

	public List<Employee> findEmmplyeeBySalaryAndSort(Long salary, Sort sort);

	public Page<Employee> findAllEmployeesWithPagination(Pageable pageable);

	// custom query which requires entity manager
	public List<Employee> findAllEmployeesUsingCriteriaApi();

}
