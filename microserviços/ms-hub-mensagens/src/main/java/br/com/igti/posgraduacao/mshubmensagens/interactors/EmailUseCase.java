package br.com.igti.posgraduacao.mshubmensagens.interactors;

import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;
import br.com.igti.posgraduacao.mshubmensagens.repositories.EmailHubRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailUseCase {
    private EmailHubRepository emailHubRepository;

    public EmailUseCase(EmailHubRepository emailHubRepository) {
        this.emailHubRepository = emailHubRepository;
    }

    public EmailHub enviarEmail(EmailHub emailHub) {
        try {
            return this.emailHubRepository.enviarEmail(emailHub);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar o email para o cliente");
        }
    }
}
