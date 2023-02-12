package br.com.igti.posgraduacao.msfraude.transportlayer.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRequisicaoDto {

    private Boolean valid_format;
    private Boolean deliverable;
    private Boolean inbox_full;
    private DomainDetails domainDetails;

}
