package br.com.igti.posgraduacao.mscadastropositivo.datasources;

import br.com.igti.posgraduacao.mscadastropositivo.datasources.repositories.MongoClienteRepository;
import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;
import br.com.igti.posgraduacao.mscadastropositivo.repositories.CadastroPositivoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CadastroPositivoDataSource implements CadastroPositivoRepository {

    private final Logger log = LoggerFactory.getLogger(CadastroPositivoDataSource.class);
    private MongoClienteRepository mongoClienteRepository;

    public CadastroPositivoDataSource(MongoClienteRepository mongoClienteRepository) {
        this.mongoClienteRepository = mongoClienteRepository;
    }

    @Override
    public List<Positivo> getAll() {
        return this.mongoClienteRepository.findAll();
    }


    @Override
    public Positivo getPositivoById(String id) {
        Optional<Positivo> positivoOptional =  this.mongoClienteRepository.findById(id);
        if (positivoOptional.isEmpty()){
          new RuntimeException("Cadastro Positivo Não Encontrado !");
        }
        return positivoOptional.get();
    }

    @Override
    public Positivo getPositivoByCpf(String cpf) {
        return this.mongoClienteRepository.consultarPorCpf(cpf);
    }

    @Override
    public Positivo inserirPositivo(Positivo positivo) {
        return this.mongoClienteRepository.save(positivo);
    }

    @Override
    public Positivo atualizarPositivo(Positivo positivo) {
        return this.mongoClienteRepository.save(positivo);
    }

    @Override
    public void excluirPositivo(String id) {
        this.mongoClienteRepository.deleteById(id);
    }
}
