package br.com.igti.posgraduacao.msfraude.repositories;



public interface FraudeRepository {

    Boolean getEmailValido(String email);
    Boolean getTelefoneValido(String telefone);
    Boolean getCepValido(String cep);
}
