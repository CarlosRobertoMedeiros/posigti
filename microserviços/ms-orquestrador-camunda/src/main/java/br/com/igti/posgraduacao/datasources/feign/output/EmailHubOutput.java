package br.com.igti.posgraduacao.datasources.feign.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailHubOutput {
    private String id;
    private String emailTo;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataEnvioEmail;
}
