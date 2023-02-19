package br.com.igti.posgraduacao.mscadastropositivo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Col_Cad_Positivo")
public class Positivo {

    @Id
    private String id; //= UUID.randomUUID();
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataInclusao = LocalDateTime.now();




}
