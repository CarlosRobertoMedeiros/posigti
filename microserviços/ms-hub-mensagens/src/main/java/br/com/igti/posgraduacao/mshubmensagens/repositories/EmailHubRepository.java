package br.com.igti.posgraduacao.mshubmensagens.repositories;


import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;

import java.io.IOException;

public interface EmailHubRepository {
    EmailHub enviarEmail(EmailHub email) throws IOException;
}
