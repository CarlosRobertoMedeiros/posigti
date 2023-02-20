package br.com.igti.posgraduacao.datasources.feign.configuration.fraude;

import org.springframework.context.annotation.Bean;

public class ClientFraudeConfiguration {

    @Bean
    public ClientFraudeErrorDecoder errorDecoder(){
        return new ClientFraudeErrorDecoder();
    }
}
