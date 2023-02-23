package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientCadastroPositivo;
import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;
import br.com.igti.posgraduacao.repositories.CadastroPositivoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroPositivoDataSource implements CadastroPositivoRepository {

    private final ClientCadastroPositivo clientCadastroPositivo;

    public CadastroPositivoDataSource(ClientCadastroPositivo clientCadastroPositivo) {
        this.clientCadastroPositivo = clientCadastroPositivo;
    }

    @Override
    public PositivoOutput inserirPositivo(PositivoInput positivoInput) {
        ResponseEntity<PositivoOutput> positivoOutput =  clientCadastroPositivo.inserirPositivo(positivoInput);
        return positivoOutput.getBody();
    }
}
