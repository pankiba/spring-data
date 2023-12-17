package com.pankiba.jpashowcase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pankiba.jpashowcase.domain.Employee;
import com.pankiba.jpashowcase.repository.EmployeeRepository;
import com.pankiba.jpashowcase.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> saveEmployees(List<Employee> employees) {
		return employeeRepository.saveAll(employees);
	}

	@Override
	public Employee findEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}

	@Override
	public List<Employee> findEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> updateEmployees(List<Employee> employees) {
		return employeeRepository.saveAll(employees);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

	@Override
	public List<Employee> findAllEmployeesJpql() {
		return employeeRepository.findAllEmployeesJpql();
	}

	@Override
	public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
		return employeeRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Employee> findByFirstNameAndLastNameIndexJpql(String firstName, String lastName) {
		return employeeRepository.findByFirstNameAndLastNameIndexJpql(firstName, lastName);
	}

	@Override
	public List<Employee> findByFirstNameAndLastNameIndexNative(String firstName, String lastName) {
		return employeeRepository.findByFirstNameAndLastNameIndexNative(firstName, lastName);
	}

	@Override
	public List<Employee> findByFirstNameAndLastNameNamedJpql(String firstName, String lastName) {
		return employeeRepository.findByFirstNameAndLastNameNamedJpql(firstName, lastName);
	}

	@Override
	public List<Employee> findByFirstNameAndLastNameNamedNative(String firstName, String lastName) {
		return employeeRepository.findByFirstNameAndLastNameIndexNative(firstName, lastName);
	}

	@Override
	public List<Employee> findEmmplyeeBySalaryAndSort(Long salary, Sort sort) {
		return employeeRepository.findEmmplyeeBySalaryAndSort(salary, sort);
	}

	@Override
	public Page<Employee> findAllEmployeesWithPagination(Pageable pageable) {
		return employeeRepository.findAllEmployeesWithPagination(pageable);
	}

	@Override
	public List<Employee> findAllEmployeesUsingCriteriaApi() {
		return employeeRepository.findAllEmployeesUsingCriteriaApi();
	}

}
