package br.com.igti.posgraduacao.mscadastronegativo.transportlayer.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NegativadoInput {

    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataInclusao;
}
