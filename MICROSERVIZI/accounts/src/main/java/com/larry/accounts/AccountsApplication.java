package com.larry.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
			title = "Accounts microservice REST API Documentation",
			description="MyCompany Accounts microservice REST API Documentation",
			version="v1",
			contact = @Contact(
					name = "Lo.merola",
					email="lo.merola@libero.it",
					url="https://www.linkedin.com/in/lorenzo-davide-merola-338a06278/"
					),
					license = @License(
							name = "Apache 2.0",
							url="https://www.linkedin.com/in/lorenzo-davide-merola-338a06278/"
					)
			)
	)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
