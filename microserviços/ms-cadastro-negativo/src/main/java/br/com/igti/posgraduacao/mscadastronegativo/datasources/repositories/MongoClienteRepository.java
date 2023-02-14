package br.com.igti.posgraduacao.mscadastronegativo.datasources.repositories;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoClienteRepository extends MongoRepository<Negativado, String> {
    @Query("{cpf: { $regex: ?0 } })")
    Negativado consultarPorCpf(String cpf);

}
