package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.prospecto.ClientNotificaProspectoDocumentosConfiguration;
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
@FeignClient(name = "prospecto",
        url = "${servicos.prospecto.notifica.url}",
        configuration = ClientNotificaProspectoDocumentosConfiguration.class
)
public interface ClientNotificaProspectoPendenciaDocumentos {

    @PostMapping(value = "/enviar-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<EmailHubOutput> enviarEmail(@Valid @RequestBody EmailHubInput emailHubInput);


}

