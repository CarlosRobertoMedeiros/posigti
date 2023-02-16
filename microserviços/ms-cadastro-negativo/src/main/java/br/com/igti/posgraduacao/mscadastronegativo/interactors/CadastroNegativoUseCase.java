package br.com.igti.posgraduacao.mscadastronegativo.interactors;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.repositories.CadastroNegativadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Negativado getNegativadoByCpf(String cpf) {
        Negativado negativadoData = this.cadastroNegativadoRepository.getNegativadoByCpf(cpf);
        if (negativadoData==null){
            new RuntimeException("Negativado sem Dados");
        }
        return negativadoData;
    }

    public Negativado inserirNegativado(Negativado negativado) {
        return this.cadastroNegativadoRepository.inserirNegativado(negativado);
    }

    public Negativado getNegativadoById(String id) {
        Negativado negativadoData = this.cadastroNegativadoRepository.getNegativadoById(id);
        if (negativadoData==null){
            new RuntimeException("Negativado sem Dados");
        }
        return negativadoData;
    }

    public Negativado atualizarNegativado(Negativado negativado, String id) {
        Negativado negativadoData = this.cadastroNegativadoRepository.getNegativadoById(id);

        if (negativadoData!=null){
            Negativado negativadoInformadoNoParametro =  negativadoData;
            negativadoInformadoNoParametro.setId(negativadoData.getId());
            negativadoInformadoNoParametro.setCpf(negativado.getCpf());
            negativadoInformadoNoParametro.setNome(negativado.getNome());
            negativadoInformadoNoParametro.setTelefone(negativado.getTelefone());
            negativadoInformadoNoParametro.setEmail(negativado.getEmail());
            negativadoInformadoNoParametro.setDataInclusao(LocalDateTime.now());
            return this.cadastroNegativadoRepository.atualizarNegativado(negativadoInformadoNoParametro);
        }else{
            new RuntimeException("Não foi possível encontrar negativados para a atualização");
        }
        return null;
    }

    public ResponseEntity<Void> excluirNegativados(String id) {
        Negativado negativadoData = this.cadastroNegativadoRepository.getNegativadoById(id);

        if (negativadoData!=null){
            this.cadastroNegativadoRepository.excluirNegativado(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            new RuntimeException("Não foi possível encontrar negativados para a exclusão");
        }
        return null;
    }
}
