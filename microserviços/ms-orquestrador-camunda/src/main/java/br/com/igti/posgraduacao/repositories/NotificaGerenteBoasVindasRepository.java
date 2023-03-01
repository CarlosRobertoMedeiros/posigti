package br.com.igti.posgraduacao.repositories;

import br.com.igti.posgraduacao.datasources.feign.input.EmailHubInput;
import br.com.igti.posgraduacao.datasources.feign.output.EmailHubOutput;

public interface NotificaGerenteBoasVindasRepository {
    EmailHubOutput enviarEmail(EmailHubInput emailHubInput);

}
