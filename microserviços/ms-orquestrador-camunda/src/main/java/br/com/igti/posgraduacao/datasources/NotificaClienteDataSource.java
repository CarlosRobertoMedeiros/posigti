package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientCadastroCliente;
import br.com.igti.posgraduacao.datasources.feign.input.ClienteInput;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.ClienteOutput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.repositories.CadastroClienteRepository;
import br.com.igti.posgraduacao.repositories.NotificaClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificaClienteDataSource implements NotificaClienteRepository {

    private final ClientNotificaCliente clientNotificaCliente;

    public NotificaClienteDataSource(ClientCadastroCliente clientCadastroCliente) {
        this.clientCadastroCliente = clientCadastroCliente;
    }

    @Override
    public EmailHubOutput enviarEmail(EmailHubInput emailHubInput) {
        return null;
    }

//    @Override
//    public ClienteOutput inserirCliente(ClienteInput clienteInput) {
//        ResponseEntity<ClienteOutput> clienteOutput =  clientCadastroCliente.inserirCliente(clienteInput);
//        return clienteOutput.getBody();
//    }
}
