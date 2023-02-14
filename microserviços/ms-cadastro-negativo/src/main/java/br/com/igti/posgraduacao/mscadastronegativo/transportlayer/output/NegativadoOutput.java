package br.com.igti.posgraduacao.mscadastronegativo.transportlayer.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class NegativadoOutput {
    private UUID id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private LocalDateTime dataInclusao;
}
