package br.com.igti.posgraduacao.msclientes.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Col_Clientes")
public class Cliente {

    @Id
    private String id;
    private String cpf;
    private String agencia = "001";
    private String conta = UUID.randomUUID().toString();
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private Double renda;
    private Double cartaoDebito;
    private Double emprestimo;
    private String email;
    private LocalDateTime dataInclusao = LocalDateTime.now();
}
