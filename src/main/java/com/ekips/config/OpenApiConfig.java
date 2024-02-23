package com.ekips.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "My Life Master Service",
                description = "my_life_master_service", version = "1.0.0",
                contact = @Contact(
                        name = "Egor Martynov",
                        email = "ekip-s@yandex.ru",
                        url = ""
                )
        )
)
public class OpenApiConfig {
}
