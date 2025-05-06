package com.mx.aplazo.BNPL.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(
                        new Info()
                                .title("BNPL API, challenge test for aplazo")
                                .version("1.0.0")
                                .description("BNPL (Buy Now, Pay Later) is a credit modality that allows " +
                                        "the purchase of products through installment payments, with the " +
                                        "first payment occurring at the time of purchase or later.")
                                .contact(
                                        new Contact()
                                                .name("Jose Javier Gamez Andrade")
                                                .email("jose.gamez.andrade@gmail.com")
                                )
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                )
                )
                .tags(Arrays.asList(
                        new Tag().name("AuthController").description("You should create an user, then login to generate a JWT token"),
                        new Tag().name("CustomerController").description("You must create a customer first"),
                        new Tag().name("LoanController").description("You feel free to create a loan for a customer")
                ));
    }
}