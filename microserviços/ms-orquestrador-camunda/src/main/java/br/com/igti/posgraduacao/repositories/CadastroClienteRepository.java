package br.com.igti.posgraduacao.repositories;

import br.com.igti.posgraduacao.datasources.feign.input.ClienteInput;
import br.com.igti.posgraduacao.datasources.feign.output.ClienteOutput;

public interface CadastroClienteRepository {
    ClienteOutput inserirCliente(ClienteInput clienteInput);

}
