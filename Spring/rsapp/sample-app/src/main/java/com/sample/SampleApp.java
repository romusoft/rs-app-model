package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@ComponentScan(value= {"com.sample","security"})
public class SampleApp {

	public static void main(String[] args) {
		SpringApplication.run(SampleApp.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Hello world";
		
	}

}
