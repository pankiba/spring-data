package com.pankiba.onetomany;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pankiba.onetomany.domain.BusinessUnit;
import com.pankiba.onetomany.domain.Employee;
import com.pankiba.onetomany.domain.Grade;
import com.pankiba.onetomany.service.BusinessUnitService;
import com.pankiba.onetomany.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataLoader implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private BusinessUnitService businessUnitService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void run(String... args) throws Exception {

		log.info("Loading test data.....");

		log.info("Save");

		BusinessUnit businessUnitOne = new BusinessUnit("ADM");

		businessUnitOne.addEmployee(new Employee("John", "McLane", "M", "john.rambo@users.noreply.github.com",
				LocalDate.parse("1970-07-30"), LocalDate.parse("2008-07-26"), Grade.Developer, 20000L));

		businessUnitOne.addEmployee(new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
				LocalDate.parse("1982-09-26"), LocalDate.parse("2005-07-21"), Grade.Lead, 30000L));

		businessUnitService.saveBusinessUnit(businessUnitOne);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, EMAIL, BIRTH_DATE, JOINING_DATE, GRADE, SALARY, BUSINESS_UNIT_ID FROM EMPLOYEE");
		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT BUSINESS_UNIT_ID, BUSINESS_UNIT_NAME FROM BUSINESS_UNIT");

		/**
		 * This will execute one query with join to fetch Employee data with
		 * BusinessUnit as well. Because, ManyToOne association's ( from Employee to
		 * Business ) fetching strategy is by default set to FetchType.EAGER, to load
		 * data eagerly.
		 */
		log.info("FindEmployeeById");
		employeeService.findEmployeeById(5002L);

		/**
		 * This will execute only one query to fetch BusinessUnit data. Because,
		 * OneToMany association's ( from Business to Employee ) fetching strategy is by
		 * default set to FetchType.LAZY, to load data lazily.
		 */

		log.info("FindBusinessUnitById");
		businessUnitService.findBusinessUnitById(1001L);

	}

}
