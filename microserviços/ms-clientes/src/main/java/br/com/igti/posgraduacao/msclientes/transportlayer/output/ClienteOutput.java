package br.com.igti.posgraduacao.msclientes.transportlayer.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteOutput {
    private String id;
    private String cpf;
    private String banco;
    private String conta;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private Double renda;
    private Double cartaoDebito;
    private Double emprestimo;
    private String email;
    private LocalDateTime dataInclusao;
}
