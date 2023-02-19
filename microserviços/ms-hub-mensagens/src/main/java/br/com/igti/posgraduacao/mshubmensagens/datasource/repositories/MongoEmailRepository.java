package br.com.igti.posgraduacao.mshubmensagens.datasource.repositories;

import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoEmailRepository extends MongoRepository<EmailHub, String> {


}
