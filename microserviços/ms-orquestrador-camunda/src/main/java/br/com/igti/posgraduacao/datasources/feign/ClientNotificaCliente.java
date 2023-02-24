package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.cadastropositivo.ClientCadastroPositivoConfiguration;
import br.com.igti.posgraduacao.datasources.feign.configuration.cliente.ClientNotificaClienteConfiguration;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "cliente",
        url = "${servicos.cliente.notifica.url}",
        configuration = ClientNotificaClienteConfiguration.class
)
public interface ClientNotificaCliente {

    @PostMapping(value = "/enviar-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<EmailHubOutput> enviarEmail(@Valid @RequestBody EmailHubInput emailHubInput);


}

