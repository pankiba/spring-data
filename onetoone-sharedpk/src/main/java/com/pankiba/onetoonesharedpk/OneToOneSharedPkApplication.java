package com.pankiba.onetoonesharedpk;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pankiba.onetoonesharedpk.util.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OneToOneSharedPkApplication {


	private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
	public static final String DEVELOPMENT_PROFILE = "dev";

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(OneToOneSharedPkApplication.class);

		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put(SPRING_PROFILE_DEFAULT, DEVELOPMENT_PROFILE);
		springApplication.setDefaultProperties(defaultProperties);

		Environment environment = springApplication.run(args).getEnvironment();

		ApplicationUtils.logApplicationStartup(environment);
	}


}
