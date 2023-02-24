package br.com.igti.posgraduacao.datasources.feign.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailHubInput {
    private String emailTo;
    private String titulo;
    private String conteudo;
}
