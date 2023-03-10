package br.com.igti.posgraduacao.transportlayer.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrquestradorSolicitarAberturaContaOutput {

    private ProcessOutput processOutput;
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
