package com.kcj.quickpoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kcj.repository")
@EntityScan("com.kcj.domain")
@ComponentScan(basePackages = {"com.kcj.controller", "com.kcj.service"})
public class QuickPollApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickPollApplication.class, args);
	}

}
