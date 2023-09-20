package com.d103.newreka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = SecurityAutoConfiguration.class)
// @SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NewrekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewrekaApplication.class, args);
	}

}
