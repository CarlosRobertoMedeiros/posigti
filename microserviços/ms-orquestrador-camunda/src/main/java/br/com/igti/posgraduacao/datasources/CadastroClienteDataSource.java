package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientCadastroCliente;
import br.com.igti.posgraduacao.datasources.feign.input.ClienteInput;
import br.com.igti.posgraduacao.datasources.feign.output.ClienteOutput;
import br.com.igti.posgraduacao.repositories.CadastroClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteDataSource implements CadastroClienteRepository {

    private final ClientCadastroCliente clientCadastroCliente;

    public CadastroClienteDataSource(ClientCadastroCliente clientCadastroCliente) {
        this.clientCadastroCliente = clientCadastroCliente;
    }

    @Override
    public ClienteOutput inserirCliente(ClienteInput clienteInput) {
        ResponseEntity<ClienteOutput> clienteOutput =  clientCadastroCliente.inserirCliente(clienteInput);
        return clienteOutput.getBody();
    }
}
