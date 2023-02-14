package br.com.igti.posgraduacao.mscadastronegativo.config;

import br.com.igti.posgraduacao.mscadastronegativo.MsCadastroNegativoApplication;
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
@ComponentScan(basePackages = { "br.com.igti.posgraduacao.mscadastronegativo" })
public class AppSwaggerConfig {


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Service Cadastro Negativo")
                        .description("Recursos do Sistema de Cadastro Negativo")
                        .version(MsCadastroNegativoApplication.getBuildPropertiesStatic().getVersion())
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("OpenAPI Specification")
                        .url("https://swagger.io/specification/"));
    }
}
