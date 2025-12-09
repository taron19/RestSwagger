package org.example.qa_guru.restswagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //описание нашего проекта в сваггере
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Simple Spring Boot API")
                        .version("1.0")
                        .description("Простой пример Spring Boot API с 4 эндпоинтами")
                        .contact(new Contact()
                                .name("Taron")
                                .email("Taron_dev@example.com")));
    }

}
