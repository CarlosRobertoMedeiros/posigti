package br.com.igti.posgraduacao.datasources.feign;

import br.com.igti.posgraduacao.datasources.feign.configuration.fraude.ClientFraudeConfiguration;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "fraude",
        url = "${servicos.fraude.url}",
        configuration = ClientFraudeConfiguration.class
)
public interface ClientFraude {
    @GetMapping("/cep/{cep}")
    @Headers({"Content-Type: application/json"})
    Boolean getCepFraudado(@PathVariable(name = "cep") String cep);

    @GetMapping("/telefone/{telefone}")
    @Headers({"Content-Type: application/json"})
    Boolean getTelefoneFraudado(@PathVariable(name = "telefone") String telefone);

    @GetMapping("/email/{email}")
    @Headers({"Content-Type: application/json"})
    Boolean getEmailFraudado(@PathVariable(name = "email") String email);

}

