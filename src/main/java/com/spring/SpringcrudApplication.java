package com.spring;


//use postman to run this
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackageClasses = DepartmentController.class)
public class SpringcrudApplication {

	public static void main(String[] args) {
		System.out.println("insdie mail");
		SpringApplication.run(SpringcrudApplication.class, args);
	}

}
