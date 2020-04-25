package com.spring.recruitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRecruitmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRecruitmentAppApplication.class, args);
		System.out.println("Hang Li - Spring Boot JPA Recruitment web app started");
		System.out.println("Spring Boot JPA web app port 8081");
	}

}
