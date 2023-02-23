package br.com.igti.posgraduacao.repositories;

import br.com.igti.posgraduacao.datasources.feign.input.NegativadoInput;
import br.com.igti.posgraduacao.datasources.feign.output.NegativadoOutput;

public interface CadastroNegativoRepository {
    public NegativadoOutput inserirNegativado(NegativadoInput negativado);

}
