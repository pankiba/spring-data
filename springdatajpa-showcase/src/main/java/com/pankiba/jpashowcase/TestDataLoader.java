package com.pankiba.jpashowcase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pankiba.jpashowcase.domain.Employee;
import com.pankiba.jpashowcase.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestDataLoader implements CommandLineRunner{
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		
		log.info("Loading test data.....");

		Resource resource = resourceLoader.getResource("classpath:test-data.json");
		ObjectMapper objectMapper = new ObjectMapper();

		try {

			List<Employee> employeeList = objectMapper.readValue(resource.getFile(),
					new TypeReference<List<Employee>>() {
					});

			log.info("Loading test data from : " + resource.getFilename() + " @ "
					+ resource.getFile().getAbsolutePath() + " which is as follows - ");

			employeeService.saveEmployees(employeeList);
			
			DisplayTableUtil.printSelect(jdbcTemplate, "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, GENDER, EMAIL, BIRTH_DATE, JOINING_DATE, GRADE, SALARY FROM EMPLOYEE");


		} catch (Exception exception) {
			log.error("Error while loading data from josn file");
			exception.printStackTrace();
		}

	
	}

}
