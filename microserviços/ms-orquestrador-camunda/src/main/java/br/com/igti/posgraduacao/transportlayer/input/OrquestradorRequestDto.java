package br.com.igti.posgraduacao.transportlayer.input;

import lombok.Data;

@Data
public class OrquestradorRequestDto {
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
