package br.com.igti.posgraduacao.repositories;

public interface FraudeRepository {

    boolean isCepFraudado(String cep);
    boolean isEmailFraudado(String email);
    boolean isTelefoneFraudado(String telefone);
}
