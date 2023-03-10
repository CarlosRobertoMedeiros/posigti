package br.com.igti.posgraduacao.mshubmensagens.transportlayer.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailHubOutput {
    private String id;
    private String emailTo;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataEnvioEmail;
}
