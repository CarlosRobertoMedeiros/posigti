package br.com.igti.posgraduacao.repositories;

import br.com.igti.posgraduacao.datasources.feign.input.PositivoInput;
import br.com.igti.posgraduacao.datasources.feign.output.PositivoOutput;

public interface CadastroPositivoRepository {
    PositivoOutput inserirPositivo(PositivoInput positivoInput);

}
