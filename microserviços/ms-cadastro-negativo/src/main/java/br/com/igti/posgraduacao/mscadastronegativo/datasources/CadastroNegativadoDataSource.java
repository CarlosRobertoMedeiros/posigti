package br.com.igti.posgraduacao.mscadastronegativo.datasources;

import br.com.igti.posgraduacao.mscadastronegativo.datasources.repositories.MongoClienteRepository;
import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.repositories.CadastroNegativadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroNegativadoDataSource implements CadastroNegativadoRepository {

    private final Logger log = LoggerFactory.getLogger(CadastroNegativadoDataSource.class);
    private MongoClienteRepository mongoClienteRepository;

    public CadastroNegativadoDataSource(MongoClienteRepository mongoClienteRepository) {
        this.mongoClienteRepository = mongoClienteRepository;
    }

    @Override
    public List<Negativado> getAll() {
        return this.mongoClienteRepository.findAll();
    }

    @Override
    public Negativado getByCpf(String cpf) {
        return mongoClienteRepository.consultarPorCpf(cpf);
    }

    @Override
    public Negativado post(Negativado negativado) {
        return this.mongoClienteRepository.save(negativado);
    }
}
