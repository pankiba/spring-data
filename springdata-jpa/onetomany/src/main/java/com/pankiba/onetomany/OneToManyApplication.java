package com.pankiba.onetomany;

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

import com.pankiba.onetomany.domain.BusinessUnit;
import com.pankiba.onetomany.domain.Employee;
import com.pankiba.onetomany.domain.Grade;
import com.pankiba.onetomany.service.BusinessUnitService;
import com.pankiba.onetomany.service.EmployeeService;
import com.pankiba.utils.displaytable.DisplayTableUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OneToManyApplication {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BusinessUnitService businessUnitService;

	@Autowired
	private EmployeeService employeeService;

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";


	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(OneToManyApplication.class);
		
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

			log.info("Save");
			
			BusinessUnit businessUnitOne = new BusinessUnit("ADM");

			businessUnitOne.addEmployee(new Employee("John", "McLane", "M", "john.rambo@users.noreply.github.com",
					parseDate("1970-07-30", "yyyy-MM-dd"), parseDate("2008-07-26", "yyyy-MM-dd"), Grade.Developer, 20000L));

			businessUnitOne.addEmployee(new Employee("Ethan", "Hunt", "M", "ethan.hunt@users.noreply.github.com",
					parseDate("1982-09-26", "yyyy-MM-dd"), parseDate("2005-07-21", "yyyy-MM-dd"), Grade.Lead, 30000L));
			
			businessUnitService.saveBusinessUnit(businessUnitOne);

			DisplayTableUtil.printTable(jdbcTemplate, "BUSINESS_UNIT", "EMPLOYEE");

			/**
			 * This will execute one query with join to fetch Employee data with BusinessUnit as well. Because, ManyToOne
			 * association's ( from Employee to Business ) fetching strategy is by default set to FetchType.EAGER, to load
			 * data eagerly.
			 */
			log.info("FindEmployeeById");
			Employee employee = employeeService.findEmployeeById(5002L);

			/**
			 * This will execute only one query to fetch BusinessUnit data. Because, OneToMany association's ( from Business
			 * to Employee ) fetching strategy is by default set to FetchType.LAZY, to load data lazily.
			 */

			log.info("FindBusinessUnitById");
			BusinessUnit businessUnit = businessUnitService.findBusinessUnitById(1001L);

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
