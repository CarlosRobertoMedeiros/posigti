package br.com.igti.posgraduacao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrquestradorSolicitarAberturaConta {
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
