package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientCadastroNegativo;
import br.com.igti.posgraduacao.datasources.feign.input.NegativadoInput;
import br.com.igti.posgraduacao.datasources.feign.output.NegativadoOutput;
import br.com.igti.posgraduacao.repositories.CadastroNegativoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroNegativoDataSource implements CadastroNegativoRepository {

    private final ClientCadastroNegativo clientCadastroNegativo;

    public CadastroNegativoDataSource(ClientCadastroNegativo clientCadastroNegativo) {
        this.clientCadastroNegativo = clientCadastroNegativo;
    }

    @Override
    public NegativadoOutput inserirNegativado(NegativadoInput negativado) {
        ResponseEntity<NegativadoOutput> negativadoOutput =  clientCadastroNegativo.inserirNegativado(negativado);
        return negativadoOutput.getBody();
    }
}
