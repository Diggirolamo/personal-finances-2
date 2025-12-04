package com.finance.personalfinance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PersonalFinanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceApplication.class, args);
	}
	@Component
	public class DebugConfig implements CommandLineRunner {

		@Value("${spring.datasource.url}")
		private String url;

		@Override
		public void run(String... args) {
			System.out.println("### DATABASE USATO: " + url);
		}
	}
}