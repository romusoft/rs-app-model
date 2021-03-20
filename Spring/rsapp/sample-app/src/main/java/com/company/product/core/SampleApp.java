package com.company.product.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.company.product" })
public class SampleApp {

	public static void main(String[] args) {
		SpringApplication.run(SampleApp.class, args);
	}
}
