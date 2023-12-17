package com.pankiba.onetooneforeignkeyasso.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeResource {

	@GetMapping(path = "/")
	public String helloWorld() {
		return "Welcome to Spring Data JPA, world of new possibilities !!";
	}

}
