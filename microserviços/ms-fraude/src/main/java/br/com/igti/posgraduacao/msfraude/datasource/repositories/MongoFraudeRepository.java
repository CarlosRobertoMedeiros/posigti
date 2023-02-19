package br.com.igti.posgraduacao.msfraude.datasource.repositories;

import br.com.igti.posgraduacao.msfraude.entities.Fraude;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoFraudeRepository extends MongoRepository<Fraude, String> {
    List<Fraude> findByEmail(String email);
    List<Fraude> findByTelefone(String telefone);
    List<Fraude> findByCep(String cep);

}
