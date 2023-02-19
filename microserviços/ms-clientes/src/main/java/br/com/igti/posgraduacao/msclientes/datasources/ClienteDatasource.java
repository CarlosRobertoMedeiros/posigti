package br.com.igti.posgraduacao.msclientes.datasources;

import br.com.igti.posgraduacao.msclientes.datasources.repositories.MongoClienteRepository;
import br.com.igti.posgraduacao.msclientes.entities.Cliente;
import br.com.igti.posgraduacao.msclientes.repositories.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteDatasource implements ClienteRepository {

    private final Logger log = LoggerFactory.getLogger(ClienteRepository.class);
    private MongoClienteRepository mongoClienteRepository;

    public ClienteDatasource(MongoClienteRepository mongoClienteRepository) {
        this.mongoClienteRepository = mongoClienteRepository;
    }

    @Override
    public List<Cliente> getAll() {
        return this.mongoClienteRepository.findAll();
    }


    @Override
    public Cliente getClienteById(String id) {
        Optional<Cliente> clienteOptional =  this.mongoClienteRepository.findById(id);
        if (clienteOptional.isEmpty()){
          new RuntimeException("Cadastro de Cliente Não Encontrado !");
        }
        return clienteOptional.get();
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        return this.mongoClienteRepository.consultarPorCpf(cpf);
    }

    @Override
    public Cliente inserirCliente(Cliente cliente) {
        return this.mongoClienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return this.mongoClienteRepository.save(cliente);
    }

    @Override
    public void excluirCliente(String id) {
        this.mongoClienteRepository.deleteById(id);
    }
}
