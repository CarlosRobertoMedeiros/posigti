package br.com.igti.posgraduacao.msclientes.datasources.repositories;

import br.com.igti.posgraduacao.msclientes.entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoClienteRepository extends MongoRepository<Cliente, String> {
    @Query("{cpf: { $regex: ?0 } })")
    Cliente consultarPorCpf(String cpf);

}
