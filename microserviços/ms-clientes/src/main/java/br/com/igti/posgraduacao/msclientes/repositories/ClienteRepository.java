package br.com.igti.posgraduacao.msclientes.repositories;

import br.com.igti.posgraduacao.msclientes.entities.Cliente;

import java.util.List;

public interface ClienteRepository {

    List<Cliente> getAll();
    Cliente getClienteById(String id);
    Cliente getClienteByCpf(String cpf);
    Cliente inserirCliente(Cliente cliente);
    Cliente atualizarCliente(Cliente cliente);
    void excluirCliente(String id);
}
