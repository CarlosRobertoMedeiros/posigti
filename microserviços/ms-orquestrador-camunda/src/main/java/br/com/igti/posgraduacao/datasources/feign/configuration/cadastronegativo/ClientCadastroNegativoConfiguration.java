package br.com.igti.posgraduacao.datasources.feign.configuration.cadastronegativo;

import org.springframework.context.annotation.Bean;

public class ClientCadastroNegativoConfiguration {

    @Bean
    public ClientCadastroNegativoErrorDecoder errorDecoder(){
        return new ClientCadastroNegativoErrorDecoder();
    }
}
