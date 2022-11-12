package com.pankiba.jpashowcase;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pankiba.jpashowcase.domain.Employee;
import com.pankiba.jpashowcase.repository.impl.BaseRepositoryImpl;
import com.pankiba.jpashowcase.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pankiba.jpashowcase.repository", repositoryBaseClass = BaseRepositoryImpl.class)
@Slf4j
public class SpringDataJpaShowcaseApplication {

	@Autowired
	private ResourceLoader resourceLoader;

	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(SpringDataJpaShowcaseApplication.class);

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

			Resource resource = resourceLoader.getResource("classpath:test-data.json");
		
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				
				List<Employee> employeeList = objectMapper.readValue(resource.getFile(),
						new TypeReference<List<Employee>>() {
						});
				
				log.info("Loading test data from : "+resource.getFilename() +" @ "+resource.getFile().getAbsolutePath()+" which is as follows - ");
				employeeList.stream().forEach(System.out::println);
				
				employeeService.saveEmployees(employeeList);

			} catch (Exception exception) {
				log.error("Error while loading data from josn file");
				exception.printStackTrace();
			}

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
