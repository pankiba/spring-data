package com.pankiba.onetoonesharedpk;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.pankiba.onetoonesharedpk.domain.Employee;
import com.pankiba.onetoonesharedpk.domain.EmployeeDetails;
import com.pankiba.onetoonesharedpk.domain.Grade;
import com.pankiba.onetoonesharedpk.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OneToOneSharedPkApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(OneToOneSharedPkApplication.class);

		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put(SPRING_PROFILE_DEFAULT, DEVELOPMENT_PROFILE);
		springApplication.setDefaultProperties(defaultProperties);

		Environment environment = springApplication.run(args).getEnvironment();
		
		log.info("Type of web application : " + springApplication.getWebApplicationType());
		logApplicationStartup(environment);
	}

	@Bean
	public CommandLineRunner loadTestData(EmployeeService employeeService) {
		return args -> {

			Employee employee1 = new Employee("John", "McLane", "M", "john.rambo@users.noreply.github.com",
					parseDate("1970-07-30", "yyyy-MM-dd"), parseDate("2008-07-26", "yyyy-MM-dd"), Grade.Developer, 20000L);

			EmployeeDetails employeeDetails1 = new EmployeeDetails("Mumbai");

			employee1.setEmployeeDetails(employeeDetails1);
			employeeDetails1.setEmployee(employee1);

			employeeService.saveEmployee(employee1);
			
			Employee employe2 = new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
					parseDate("1982-09-26", "yyyy-MM-dd"), parseDate("2005-07-21", "yyyy-MM-dd"), Grade.Lead, 30000L);
			
			EmployeeDetails employeeDetails2 = new EmployeeDetails("Pune");
			
			employe2.setEmployeeDetails(employeeDetails2);
			employeeDetails2.setEmployee(employe2);
			
			employeeService.saveEmployee(employe2);

			DisplayTableUtil.printTable(jdbcTemplate, "EMPLOYEE", "EMPLOYEE_DETAILS");

		};
	}

	public static void logApplicationStartup(Environment environment) {

		String protocol = "http";

		if (environment.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String serverPort = environment.getProperty("server.port");
		String contextPath = environment.getProperty("server.servlet.context-path");

		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}

		String hostAddress = "localhost";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException unknownHostException) {
			log.warn("The host name could not be determined, using 'localhost' as fallback");
		}

		String[] profiles = environment.getActiveProfiles();

		if (profiles.length == 0) {
			log.info("No active profile set, falling back to default profiles: {} ",
					Arrays.toString(environment.getDefaultProfiles()));
			profiles = environment.getDefaultProfiles();
		}

		log.info("Spring Framework Version : {}, Spring Boot Version : {}", SpringVersion.getVersion(),
				SpringBootVersion.getVersion());
		log.info("\n------------------------------------------------------------------------------\n\t"
				+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}{}\n\t"
				+ "External: \t{}://{}:{}{}\n\t"
				+ "Profile(s): \t{}\n------------------------------------------------------------------------------",
				environment.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol,
				hostAddress, serverPort, contextPath, profiles);

	}

}
