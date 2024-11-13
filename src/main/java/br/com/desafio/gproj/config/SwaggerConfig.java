package br.com.desafio.gproj.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestão de Projetos",
                version = "1.0",
                description = "Documentação da API para gerenciamento de projetos"
        )
)
public class SwaggerConfig {

}
