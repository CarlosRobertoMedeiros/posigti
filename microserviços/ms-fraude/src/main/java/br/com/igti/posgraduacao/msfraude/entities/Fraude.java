package br.com.igti.posgraduacao.msfraude.entities;

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
@Document(collection = "Col_Fraude")
public class Fraude {

    @Id
    private String id;
    private String telefone;
    private String cep;
    private String email;
    private LocalDateTime dataInclusao = LocalDateTime.now();
}
