package br.com.igti.posgraduacao.msfraude.transportlayer.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FraudeOutput {
    private String id;
    private String email;
    private String telefone;
    private String cep;
    private LocalDateTime dataInclusao;
}
