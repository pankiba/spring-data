package com.pankiba.jpashowcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.pankiba.jpashowcase.repository.impl.BaseRepositoryImpl;
import com.pankiba.jpashowcase.util.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pankiba.jpashowcase.repository", repositoryBaseClass = BaseRepositoryImpl.class)
@Slf4j
public class SpringDataJpaShowcaseApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(SpringDataJpaShowcaseApplication.class);

		log.info("Type of web application : " + springApplication.getWebApplicationType());

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */

		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);

	}
}
