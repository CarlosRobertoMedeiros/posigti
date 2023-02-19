package br.com.igti.posgraduacao.mshubmensagens.repositories;


import br.com.igti.posgraduacao.mshubmensagens.entities.Email;

public interface EmailRepository {
    Email enviarEmail(Email email);
}
