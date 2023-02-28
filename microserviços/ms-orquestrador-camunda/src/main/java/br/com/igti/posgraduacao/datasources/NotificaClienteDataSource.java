package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientCadastroCliente;
import br.com.igti.posgraduacao.datasources.feign.ClientNotificaCliente;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.repositories.NotificaClienteRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class NotificaClienteDataSource implements NotificaClienteRepository {

    private final ClientNotificaCliente clientNotificaCliente;

    public NotificaClienteDataSource(ClientNotificaCliente clientNotificaCliente) {
        this.clientNotificaCliente = clientNotificaCliente;
    }

    @Override
    public EmailHubOutput enviarEmail(EmailHubInput emailHubInput) {
        ResponseEntity<EmailHubOutput>  emailOutput = clientNotificaCliente.enviarEmail(emailHubInput);
        return emailOutput.getBody();
    }

}
