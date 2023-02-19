package br.com.igti.posgraduacao.mshubmensagens.datasource.repositories;

import br.com.igti.posgraduacao.mshubmensagens.entities.Email;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoEmailRepository extends MongoRepository<Email, String> {


}
