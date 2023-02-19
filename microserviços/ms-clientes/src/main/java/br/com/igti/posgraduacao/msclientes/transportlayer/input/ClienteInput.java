package br.com.igti.posgraduacao.msclientes.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteInput {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private Double renda;
    private Double cartaoDebito;
    private Double emprestimo;
    private String email;
}
