package com.pankiba.jpashowcase.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.jpashowcase.domain.Employee;
import com.pankiba.jpashowcase.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DataJpaShowCaseResource {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		log.info("Save - Single Entity");
		Employee savedEmployee = employeeService.saveEmployee(employee);

		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> findEmployee(@PathVariable Long employeeId) {

		log.info("Read - Single Entity");
		Employee employee = employeeService.findEmployee(employeeId);
		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = " + employeeId);

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAEmployees() {

		log.info("Read - All Entities");
		List<Employee> employeeList = employeeService.findEmployees();
		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT * FROM EMPLOYEE");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@DeleteMapping("/employees")
	public void deleteEmployee(@RequestBody Employee employee) {

		log.info("Delete - Single Entity");
		employeeService.deleteEmployee(employee);
	}

	@GetMapping("/findall-employees-jpql")
	public ResponseEntity<List<Employee>> findAllEmployeesJpql() {

		log.info("Read - findAllEmployeesJpql");
		List<Employee> employeeList = employeeService.findAllEmployeesJpql();

		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT * FROM EMPLOYEE");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findby-firstname-and-lastname/{firstName}/{lastName}")
	public ResponseEntity<List<Employee>> findByFirstNameAndLastName(@PathVariable String firstName,
			@PathVariable String lastName) {

		log.info("Read - findByFirstNameAndLastName");
		List<Employee> employeeList = employeeService.findByFirstNameAndLastName(firstName, lastName);
		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT * FROM EMPLOYEE WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findby-firstname-and-lastname-indexjpql/{firstName}/{lastName}")
	public ResponseEntity<List<Employee>> findByFirstNameAndLastNameIndexJpql(@PathVariable String firstName,
			@PathVariable String lastName) {

		log.info("Read - findAllEmployeesJpql");
		List<Employee> employeeList = employeeService.findByFirstNameAndLastNameIndexJpql(firstName, lastName);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT * FROM EMPLOYEE WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findby-firstname-and-lastname-indexnative/{firstName}/{lastName}")
	public ResponseEntity<List<Employee>> findByFirstNameAndLastNameIndexNative(@PathVariable String firstName,
			@PathVariable String lastName) {

		log.info("Read - findByFirstNameAndLastNameIndexNative");
		List<Employee> employeeList = employeeService.findByFirstNameAndLastNameIndexNative(firstName, lastName);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT * FROM EMPLOYEE WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findby-firstname-and-lastname-namedjpql/{firstName}/{lastName}")
	public ResponseEntity<List<Employee>> findByFirstNameAndLastNameNamedJpql(@PathVariable String firstName,
			@PathVariable String lastName) {

		log.info("Read - findByFirstNameAndLastNameNamedJpql");
		List<Employee> employeeList = employeeService.findByFirstNameAndLastNameNamedJpql(firstName, lastName);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT * FROM EMPLOYEE WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findby-firstname-and-lastname-namednative/{firstName}/{lastName}")
	public ResponseEntity<List<Employee>> findByFirstNameAndLastNameNamedNative(@PathVariable String firstName,
			@PathVariable String lastName) {

		log.info("Read - findByFirstNameAndLastNameNamedNative");
		List<Employee> employeeList = employeeService.findByFirstNameAndLastNameNamedNative(firstName, lastName);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT * FROM EMPLOYEE WHERE FIRST_NAME = '" + firstName + "' AND LAST_NAME = '" + lastName + "'");

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	@GetMapping("/findemployeesby-salary-and-sort/{salary}")
	public ResponseEntity<List<Employee>> findEmmplyeeBySalaryAndSort(@PathVariable Long salary) {

		log.info("Read - findByFirstNameAndLastNameNamedNative");
		List<Employee> employeeList = employeeService.findEmmplyeeBySalaryAndSort(salary,
				Sort.by("salary").descending());

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

}
