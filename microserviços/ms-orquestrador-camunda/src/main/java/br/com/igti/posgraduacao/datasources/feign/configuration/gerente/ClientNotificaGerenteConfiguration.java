package br.com.igti.posgraduacao.datasources.feign.configuration.gerente;

import org.springframework.context.annotation.Bean;

public class ClientNotificaGerenteConfiguration {

    @Bean
    public ClientNotificaGerenteErrorDecoder errorDecoder(){
        return new ClientNotificaGerenteErrorDecoder();
    }
}
