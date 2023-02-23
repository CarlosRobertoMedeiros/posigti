package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.cadastropositivo.ClientCadastroPositivoConfiguration;
import br.com.igti.posgraduacao.datasources.feign.input.ClienteInput;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.ClienteOutput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "cadastrocliente",
        url = "${servicos.cadastrocliente.url}",
        configuration = ClientCadastroPositivoConfiguration.class
)
public interface ClientCadastroCliente {

    @PostMapping(value = "/clientes", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ClienteOutput> inserirCliente(@Valid @RequestBody ClienteInput clienteInput);


}

