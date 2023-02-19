package br.com.igti.posgraduacao.mshubmensagens.interactors;

import br.com.igti.posgraduacao.mshubmensagens.entities.Email;
import br.com.igti.posgraduacao.mshubmensagens.repositories.EmailRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailUseCase {
    private EmailRepository emailRepository;

    public EmailUseCase(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public Email enviarEmail(Email email) {
        return this.emailRepository.enviarEmail(email);
    }
}
