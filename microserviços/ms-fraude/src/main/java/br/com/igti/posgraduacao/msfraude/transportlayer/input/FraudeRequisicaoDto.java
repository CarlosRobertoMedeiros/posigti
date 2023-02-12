package br.com.igti.posgraduacao.msfraude.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FraudeRequisicaoDto {
    private String success;
    private Error error;
    private DataRequisicaoDto data;

}
