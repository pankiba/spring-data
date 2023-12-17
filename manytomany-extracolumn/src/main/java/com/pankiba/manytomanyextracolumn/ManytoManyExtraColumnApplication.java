package com.pankiba.manytomanyextracolumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pankiba.manytomanyextracolumn.util.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ManytoManyExtraColumnApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ManytoManyExtraColumnApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);
	}

}
