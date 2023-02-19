package br.com.igti.posgraduacao.msfraude.repositories;


import br.com.igti.posgraduacao.msfraude.entities.Fraude;

public interface FraudeRepository {

    Boolean getEmailFraudado(String email);
    Boolean getTelefoneFraudado(String telefone);
    Boolean getCepFraudado(String cep);

    Fraude inserirFraude(Fraude fraude);
}
