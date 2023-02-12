package br.com.igti.posgraduacao.msfraude.datasource;

public class FraudeDadosMock {
    private String email;
    private String telefone;
    private String cep;

    public FraudeDadosMock(String email, String telefone, String cep) {
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
