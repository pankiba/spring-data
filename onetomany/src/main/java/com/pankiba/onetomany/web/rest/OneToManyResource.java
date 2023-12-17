package com.pankiba.onetomany.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.onetomany.domain.BusinessUnit;
import com.pankiba.onetomany.domain.Employee;
import com.pankiba.onetomany.service.BusinessUnitService;
import com.pankiba.onetomany.service.EmployeeService;

@RestController
public class OneToManyResource {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BusinessUnitService businessUnitService;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.findAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/businessunits")
	public ResponseEntity<List<BusinessUnit>> getBusinessUnits() {
		return new ResponseEntity<List<BusinessUnit>>(businessUnitService.findAllBusinessUnits(), HttpStatus.OK);
	}

}
