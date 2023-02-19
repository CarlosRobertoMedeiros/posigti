package br.com.igti.posgraduacao.mscadastropositivo.transportlayer.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositivoOutput {
    private String id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataInclusao;
}
