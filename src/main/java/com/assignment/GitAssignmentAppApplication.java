package com.assignment;

import org.apache.naming.ContextAccessController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.assignment.controller.Controller;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class GitAssignmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitAssignmentAppApplication.class, args);
	}

}
 