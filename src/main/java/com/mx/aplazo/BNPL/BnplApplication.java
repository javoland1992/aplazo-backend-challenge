package com.mx.aplazo.BNPL;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "BNPL API, callenge test for aplazo",
				version = "1.0.0",
				description = "BNPL (Buy Now, Pay Later) is a credit modality that allows the purchase of products through installment payments, with the first payment occurring at the time of purchase or later.",
				contact = @Contact(name = "Jose Javier Gamez Andrad", email = "jose.gamez.andrade@gmail.com")
		)
)
public class BnplApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnplApplication.class, args);
	}

}
