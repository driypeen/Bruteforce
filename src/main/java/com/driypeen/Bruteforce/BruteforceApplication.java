package com.driypeen.Bruteforce;

import com.driypeen.Bruteforce.security.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters =
		{@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfiguration.class)})
public class BruteforceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BruteforceApplication.class, args);
	}
}
