package br.com.igti.posgraduacao.mscadastronegativo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Document
public class Negativado {

    @Id
    private UUID id = UUID.randomUUID();
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataInclusao = LocalDateTime.now();




}
