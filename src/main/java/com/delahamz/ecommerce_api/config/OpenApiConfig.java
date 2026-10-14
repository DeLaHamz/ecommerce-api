package com.delahamz.ecommerce_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("E-Commerce REST API")
                    .version("1.0.0")
                    .description("API REST de gestion de catalogue produits, validation de commandes et décrémentation transactionnelle des stocks.")
                    .contact(new Contact()
                            .name("Hamza EL BOUBAKRI")
                            .email("hamzaelboubakri16@gmail.com")
                            .url("https://github.com/DeLaHamz")
                            )
                );
    }
}
