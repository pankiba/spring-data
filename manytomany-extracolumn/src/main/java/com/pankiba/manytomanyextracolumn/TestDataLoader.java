package com.pankiba.manytomanyextracolumn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pankiba.manytomanyextracolumn.domain.Employee;
import com.pankiba.manytomanyextracolumn.domain.Grade;
import com.pankiba.manytomanyextracolumn.domain.Project;
import com.pankiba.manytomanyextracolumn.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataLoader implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public void run(String... args) throws Exception {

		log.info("Loading test data.....");

		log.info("Save");

		List<Employee> employeeList = new ArrayList<>();

		Employee employee1 = new Employee("John", "McLane", "M", "john.rambo@users.noreply.github.com",
				LocalDate.parse("1970-07-30"), LocalDate.parse("2008-07-26"), Grade.Developer, 20000L);

		Employee employee2 = new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
				LocalDate.parse("1982-09-26"), LocalDate.parse("2005-07-21"), Grade.Lead, 30000L);

		Employee employee3 = new Employee("Jery", "Maguire", "M", "jery.maguire@users.noreply.github.com",
				LocalDate.parse("1959-06-08"), LocalDate.parse("1994-04-26"), Grade.Lead, 30000L);

		Project project1 = new Project("SMART");
		Project project2 = new Project("Artificial Inteligence");

		employee1.addEmployeeProject(project1);
		employee1.addEmployeeProject(project2);

		employeeList.add(employee1);

		employee2.addEmployeeProject(project1);
		employee3.addEmployeeProject(project2);

		employeeList.add(employee2);
		employeeList.add(employee3);

		employeeService.saveEmployees(employeeList);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, EMAIL, BIRTH_DATE, JOINING_DATE, GRADE, SALARY FROM EMPLOYEE");

		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT PROJECT_ID, NAME FROM PROJECT");

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT ADMISSION_DATE, EMPLOYEE_EMPLOYEE_ID, PROJECT_PROJECT_ID FROM EMPLOYEE_PROJECT");

	}

}
