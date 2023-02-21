package br.com.igti.posgraduacao.datasources;

import br.com.igti.posgraduacao.datasources.feign.ClientFraude;
import br.com.igti.posgraduacao.repositories.FraudeRepository;
import org.springframework.stereotype.Service;

@Service
public class FraudeDataSource implements FraudeRepository {

    private static final String MESSAGE_ERROR = "Falha ao recuperar os dados de Fraude!";
    private final ClientFraude clientFraude;

    public FraudeDataSource(ClientFraude clientFraude) {
        this.clientFraude = clientFraude;
    }

    @Override
    public boolean isCepFraudado(String cep) {
        return clientFraude.getCepFraudado(cep);
    }

    @Override
    public boolean isEmailFraudado(String email) {
        return clientFraude.getEmailFraudado(email);
    }

    @Override
    public boolean isTelefoneFraudado(String telefone) {
        return clientFraude.getTelefoneFraudado(telefone);
    }
}
