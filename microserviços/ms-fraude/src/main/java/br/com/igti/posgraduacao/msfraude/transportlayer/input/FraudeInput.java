package br.com.igti.posgraduacao.msfraude.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FraudeInput {
    private String email;
    private String telefone;
    private String cep;
}
