package com.pankiba.onetooneforeignkeyasso;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pankiba.onetooneforeignkeyasso.domain.Address;
import com.pankiba.onetooneforeignkeyasso.domain.Employee;
import com.pankiba.onetooneforeignkeyasso.domain.Grade;
import com.pankiba.onetooneforeignkeyasso.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataLoader implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

		log.info("Loading test data.....");

		Employee employee1 = new Employee("John", "McLane", "M", "john.rambo@users.noreply.github.com",
				LocalDate.parse("1970-07-30"), LocalDate.parse("2008-07-26"), Grade.Developer, 20000L);

		Address employeeAddress1 = new Address("First Avenue", "Mumbai", "MH");

		employee1.setEmployeeAddress(employeeAddress1);
		employeeAddress1.setEmployee(employee1);

		employeeService.saveEmployee(employee1);

		Employee employe2 = new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
				LocalDate.parse("1982-09-26"), LocalDate.parse("2005-07-21"), Grade.Lead, 30000L);

		Address employeeAddress2 = new Address("Ten Avenue", "Mumbai", "MH");

		employe2.setEmployeeAddress(employeeAddress2);
		employeeAddress2.setEmployee(employe2);

		employeeService.saveEmployee(employe2);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, EMAIL, BIRTH_DATE, JOINING_DATE, GRADE, SALARY FROM EMPLOYEE");
		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT ADDRESS_ID, STREET, CITY, STATE FROM ADDRESS");

	}

}
