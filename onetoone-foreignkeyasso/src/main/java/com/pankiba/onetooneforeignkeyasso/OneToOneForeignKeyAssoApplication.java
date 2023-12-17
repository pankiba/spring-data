package com.pankiba.onetooneforeignkeyasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.pankiba.onetooneforeignkeyasso.util.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class OneToOneForeignKeyAssoApplication {

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(OneToOneForeignKeyAssoApplication.class);
		
		/*
		 * setting default profile as DEV if no other profile is configured. This needs
		 * to be done before calling run method on SpringApplication
		 */
		ApplicationUtils.setDefautlProfile(springApplication);
		Environment environment = springApplication.run(args).getEnvironment();
		ApplicationUtils.logApplicationStartup(environment);
	}

}
