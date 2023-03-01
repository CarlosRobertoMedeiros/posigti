package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.cliente.ClientNotificaClienteConfiguration;
import br.com.igti.posgraduacao.datasources.feign.configuration.gerente.ClientNotificaGerenteConfiguration;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "gerente",
        url = "${servicos.gerente.notifica.url}",
        configuration = ClientNotificaGerenteConfiguration.class
)
public interface ClientNotificaGerenteBoasVindas {

    @PostMapping(value = "/enviar-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<EmailHubOutput> enviarEmail(@Valid @RequestBody EmailHubInput emailHubInput);


}

