package br.com.igti.posgraduacao.datasources.feign.configuration.cadastrocliente;

import org.springframework.context.annotation.Bean;

public class ClientCadastroClienteConfiguration {

    @Bean
    public ClientCadastroClienteErrorDecoder errorDecoder(){
        return new ClientCadastroClienteErrorDecoder();
    }
}
