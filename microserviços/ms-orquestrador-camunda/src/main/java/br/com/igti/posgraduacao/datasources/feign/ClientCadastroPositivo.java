package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.cadastropositivo.ClientCadastroPositivoConfiguration;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "cadastropositivo",
        url = "${servicos.cadastropositivo.url}",
        configuration = ClientCadastroPositivoConfiguration.class
)
public interface ClientCadastroPositivo {

    @PostMapping(value = "/positivos", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<PositivoOutput> inserirPositivo(@Valid @RequestBody PositivoInput positivoInput);


}

