package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientNotificaProspectoPendenciaDocumentos;
import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;
import br.com.igti.posgraduacao.repositories.NotificaClienteRepository;
import br.com.igti.posgraduacao.repositories.NotificaProspectoPendenciaDocumentosRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificaProspectoPendenciaDocumentosDataSource implements NotificaProspectoPendenciaDocumentosRepository {

    private final ClientNotificaProspectoPendenciaDocumentos clientNotificaProspectoPendenciaDocumentos;

    public NotificaProspectoPendenciaDocumentosDataSource(ClientNotificaProspectoPendenciaDocumentos clientNotificaProspectoPendenciaDocumentos) {
        this.clientNotificaProspectoPendenciaDocumentos = clientNotificaProspectoPendenciaDocumentos;
    }

    @Override
    public EmailHubOutput enviarEmail(EmailHubInput emailHubInput) {
        ResponseEntity<EmailHubOutput>  emailOutput = clientNotificaProspectoPendenciaDocumentos.enviarEmail(emailHubInput);
        return emailOutput.getBody();
    }

}
