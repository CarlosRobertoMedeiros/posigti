package br.com.igti.posgraduacao.datasources.feign.configuration.cliente;

import org.springframework.context.annotation.Bean;

public class ClientNotificaClienteConfiguration {

    @Bean
    public ClientNotificaClienteErrorDecoder errorDecoder(){
        return new ClientNotificaClienteErrorDecoder();
    }
}
