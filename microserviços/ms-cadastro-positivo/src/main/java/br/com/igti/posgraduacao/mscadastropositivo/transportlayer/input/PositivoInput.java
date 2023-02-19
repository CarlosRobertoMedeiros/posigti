package br.com.igti.posgraduacao.mscadastropositivo.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositivoInput {
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
}
