package br.com.igti.posgraduacao.datasources.feign.configuration.prospecto;

import org.springframework.context.annotation.Bean;

public class ClientNotificaProspectoDocumentosConfiguration {

    @Bean
    public ClientNotificaProspectoDocumentosErrorDecoder errorDecoder(){
        return new ClientNotificaProspectoDocumentosErrorDecoder();
    }
}
