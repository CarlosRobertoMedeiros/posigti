package br.com.igti.posgraduacao.mshubmensagens.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailInput {
    private String email;
    private String assunto;
    private String conteudo;
}
