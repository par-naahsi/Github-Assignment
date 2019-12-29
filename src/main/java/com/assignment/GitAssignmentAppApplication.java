package com.assignment;

import org.apache.naming.ContextAccessController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.assignment.controller.Controller;

@SpringBootApplication(scanBasePackages={"com.assignment.service.AppServices"})
@ComponentScan
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})

public class GitAssignmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitAssignmentAppApplication.class, args);
	}

}
 