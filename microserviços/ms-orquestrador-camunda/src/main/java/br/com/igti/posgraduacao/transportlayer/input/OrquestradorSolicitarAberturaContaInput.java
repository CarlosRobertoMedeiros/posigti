package br.com.igti.posgraduacao.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrquestradorSolicitarAberturaContaInput {

    private String cpf;
    private String nome;
    private String endereco;
    private String ddd;
    private String telefone;
    private String email;
    private String cep;
    private Double renda;
    private Double cartaoDebito;
    private Double emprestimo;
}
