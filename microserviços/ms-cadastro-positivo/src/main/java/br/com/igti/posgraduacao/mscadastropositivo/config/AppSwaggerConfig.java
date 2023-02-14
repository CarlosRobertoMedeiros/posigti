package br.com.igti.posgraduacao.mscadastropositivo.config;

import br.com.igti.posgraduacao.mscadastropositivo.MsCadastroPositivoApplication;
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
@ComponentScan(basePackages = { "br.com.igti.posgraduacao.mscadastropositivo" })
public class AppSwaggerConfig {


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Service Cadastro Positivo")
                        .description("Recursos do Sistema de Cadastro Positivo")
                        .version(MsCadastroPositivoApplication.getBuildPropertiesStatic().getVersion())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("OpenAPI Specification")
                        .url("https://swagger.io/specification/"));
    }
}
