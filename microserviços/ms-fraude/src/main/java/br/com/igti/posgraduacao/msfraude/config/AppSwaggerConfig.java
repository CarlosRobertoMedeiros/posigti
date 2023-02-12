package br.com.igti.posgraduacao.msfraude.config;

import br.com.igti.posgraduacao.msfraude.MsFraudeApplication;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "br.com.igti.posgraduacao.msfraude" })
public class AppSwaggerConfig {


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Service Fraude")
                        .description("Recursos do Sistema de Fraude")
                        .version(MsFraudeApplication.getBuildPropertiesStatic().getVersion())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("OpenAPI Specification")
                        .url("https://swagger.io/specification/"));
    }
}
