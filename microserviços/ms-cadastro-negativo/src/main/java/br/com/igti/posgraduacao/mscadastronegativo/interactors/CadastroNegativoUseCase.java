package br.com.igti.posgraduacao.mscadastronegativo.interactors;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.repositories.CadastroNegativadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroNegativoUseCase {

    private final CadastroNegativadoRepository cadastroNegativadoRepository;

    public CadastroNegativoUseCase(CadastroNegativadoRepository cadastroNegativadoRepository) {
        this.cadastroNegativadoRepository = cadastroNegativadoRepository;
    }

    public List<Negativado> getAll() {
        return this.cadastroNegativadoRepository.getAll();
    }


    public Negativado getByCpf(String cpf) {
        return this.cadastroNegativadoRepository.getByCpf(cpf);
    }

    public Negativado post(Negativado negativado) {
        return this.cadastroNegativadoRepository.post(negativado);
    }


}
