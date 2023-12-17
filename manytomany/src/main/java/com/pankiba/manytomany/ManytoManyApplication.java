package com.pankiba.manytomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pankiba.manytomany.util.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ManytoManyApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(ManytoManyApplication.class);

		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);

	}

}
