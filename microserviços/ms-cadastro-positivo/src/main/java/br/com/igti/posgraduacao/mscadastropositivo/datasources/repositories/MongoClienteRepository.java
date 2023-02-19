package br.com.igti.posgraduacao.mscadastropositivo.datasources.repositories;

import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoClienteRepository extends MongoRepository<Positivo, String> {
    @Query("{cpf: { $regex: ?0 } })")
    Positivo consultarPorCpf(String cpf);

}
