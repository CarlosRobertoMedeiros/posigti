package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.cadastronegativo.ClientCadastroNegativoConfiguration;
import br.com.igti.posgraduacao.datasources.feign.input.NegativadoInput;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.NegativadoOutput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "cadastronegativo",
        url = "${servicos.cadastronegativo.url}",
        configuration = ClientCadastroNegativoConfiguration.class
)
public interface ClientCadastroNegativo {

    @PostMapping(value = "/negativados", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<NegativadoOutput> inserirNegativado(@Valid @RequestBody NegativadoInput negativadoInput);


}

