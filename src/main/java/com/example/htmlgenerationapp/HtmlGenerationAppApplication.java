package com.example.htmlgenerationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class HtmlGenerationAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(HtmlGenerationAppApplication.class, args);
	}
}
