package br.com.igti.posgraduacao.msfraude.datasource;

import br.com.igti.posgraduacao.msfraude.datasource.repositories.MongoFraudeRepository;
import br.com.igti.posgraduacao.msfraude.entities.Fraude;
import br.com.igti.posgraduacao.msfraude.repositories.FraudeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FraudeDatasource implements FraudeRepository {

    private final Logger log = LoggerFactory.getLogger(FraudeDatasource.class);
    private MongoFraudeRepository mongoFraudeRepository;

    public FraudeDatasource(MongoFraudeRepository mongoFraudeRepository) {
        this.mongoFraudeRepository = mongoFraudeRepository;
    }

    @Override
    public Boolean getEmailFraudado(String email) {
        List<Fraude> fraudeList =  this.mongoFraudeRepository.findByEmail(email);
        return fraudeList.size() > 0;
    }

    @Override
    public Boolean getTelefoneFraudado(String telefone) {
        List<Fraude> fraudeList =  this.mongoFraudeRepository.findByTelefone(telefone);
        return fraudeList.size() > 0;
    }

    @Override
    public Boolean getCepFraudado(String cep) {
        List<Fraude> fraudeList =  this.mongoFraudeRepository.findByCep(cep);
        return fraudeList.size() > 0;
    }

    @Override
    public Fraude inserirFraude(Fraude fraude) {
        return this.mongoFraudeRepository.save(fraude);
    }
}
