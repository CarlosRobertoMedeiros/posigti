package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientNotificaCliente;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.repositories.NotificaGerenteBoasVindasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificaGerenteBoasVindasDataSource implements NotificaGerenteBoasVindasRepository {

    private final ClientNotificaCliente clientNotificaCliente;

    public NotificaGerenteBoasVindasDataSource(ClientNotificaCliente clientNotificaCliente) {
        this.clientNotificaCliente = clientNotificaCliente;
    }

    @Override
    public EmailHubOutput enviarEmail(EmailHubInput emailHubInput) {
        ResponseEntity<EmailHubOutput>  emailOutput = clientNotificaCliente.enviarEmail(emailHubInput);
        return emailOutput.getBody();
    }

}
