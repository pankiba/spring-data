package com.pankiba.manytomany;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.pankiba.manytomany.domain.Employee;
import com.pankiba.manytomany.domain.Grade;
import com.pankiba.manytomany.domain.Project;
import com.pankiba.manytomany.service.EmployeeService;
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
				parseDate("1970-07-30", "yyyy-MM-dd"), parseDate("2008-07-26", "yyyy-MM-dd"), Grade.Developer,
				20000L);

		Employee employee2 = new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
				parseDate("1982-09-26", "yyyy-MM-dd"), parseDate("2005-07-21", "yyyy-MM-dd"), Grade.Lead, 30000L);

		Employee employee3 = new Employee("Jery", "Maguire", "M", "jery.maguire@users.noreply.github.com",
				parseDate("1959-06-08", "yyyy-MM-dd"), parseDate("1994-04-26", "yyyy-MM-dd"), Grade.Lead, 30000L);

		Project project1 = new Project("SMART");
		Project project2 = new Project("Artificial Inteligence");

		employee1.addProject(project2);
		employee1.addProject(project2);

		employeeList.add(employee1);

		employee2.addProject(project1);
		employee3.addProject(project2);

		employeeList.add(employee2);
		employeeList.add(employee3);

		employeeService.saveEmployees(employeeList);

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, EMAIL, BIRTH_DATE, JOINING_DATE, GRADE, SALARY FROM EMPLOYEE");

		DisplayTableUtil.printSelect(jdbcTemplate, "SELECT PROJECT_ID, NAME FROM PROJECT");

		DisplayTableUtil.printSelect(jdbcTemplate,
				"SELECT EMPLOYEE_ID, PROJECT_ID FROM EMPLOYEE_PROJECT");

	}

}
