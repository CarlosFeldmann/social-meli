package br.com.mercadolivre.bootcampw2.grupo11.socialmeli.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Social Meli API")
                        .description(
                                "API for creating following and post system to connect sellers and costumers")
                        .version("0.0.1")

                )
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/lmarizmeli/social-meli/"));
    }


}
