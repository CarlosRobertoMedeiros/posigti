package br.com.igti.posgraduacao.datasources.feign.configuration.cadastropositivo;

import org.springframework.context.annotation.Bean;

public class ClientCadastroPositivoConfiguration {

    @Bean
    public ClientCadastroPositivoErrorDecoder errorDecoder(){
        return new ClientCadastroPositivoErrorDecoder();
    }
}
