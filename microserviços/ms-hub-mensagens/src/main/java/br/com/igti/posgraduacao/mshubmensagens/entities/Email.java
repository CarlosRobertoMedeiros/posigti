package br.com.igti.posgraduacao.mshubmensagens.entities;

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
@Document(collection = "Col_Email_Enviado")
public class Email {
    @Id
    private String id;
    private String emailTo;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataEnvioEmail = LocalDateTime.now();;
}
