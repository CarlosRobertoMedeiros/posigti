package br.com.igti.posgraduacao.mscadastropositivo.interactors;

import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;
import br.com.igti.posgraduacao.mscadastropositivo.repositories.CadastroPositivoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CadastroPositivoUseCase {

    private final CadastroPositivoRepository cadastroPositivoRepository;

    public CadastroPositivoUseCase(CadastroPositivoRepository cadastroPositivoRepository) {
        this.cadastroPositivoRepository = cadastroPositivoRepository;
    }

    public List<Positivo> getAll() {
        return this.cadastroPositivoRepository.getAll();
    }

    public Positivo getPositivoByCpf(String cpf) {
        Positivo positivoData = this.cadastroPositivoRepository.getPositivoByCpf(cpf);
        if (positivoData==null){
            new RuntimeException("Cadastro Positivo sem Dados");
        }
        return positivoData;
    }

    public Positivo inserirPositivo(Positivo positivo) {
        return this.cadastroPositivoRepository.inserirPositivo(positivo);
    }

    public Positivo getPositivoById(String id) {
        Positivo positivoData = this.cadastroPositivoRepository.getPositivoById(id);
        if (positivoData==null){
            new RuntimeException("Cadastro Positivo sem Dados");
        }
        return positivoData;
    }

    public Positivo atualizarPositivo(Positivo positivo, String id) {
        Positivo positivoData = this.cadastroPositivoRepository.getPositivoById(id);

        if (positivoData!=null){
            Positivo positivoInformadoNoParametro =  positivoData;
            positivoInformadoNoParametro.setId(positivoData.getId());
            positivoInformadoNoParametro.setCpf(positivo.getCpf());
            positivoInformadoNoParametro.setNome(positivo.getNome());
            positivoInformadoNoParametro.setTelefone(positivo.getTelefone());
            positivoInformadoNoParametro.setEmail(positivo.getEmail());
            positivoInformadoNoParametro.setDataInclusao(LocalDateTime.now());
            return this.cadastroPositivoRepository.atualizarPositivo(positivoInformadoNoParametro);
        }else{
            new RuntimeException("Não foi possível encontrar cadastros positivos para a atualização");
        }
        return null;
    }

    public ResponseEntity<Void> excluirPositivo(String id) {
        Positivo positivoData = this.cadastroPositivoRepository.getPositivoById(id);

        if (positivoData!=null){
            this.cadastroPositivoRepository.excluirPositivo(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            new RuntimeException("Não foi possível encontrar negativados para a exclusão");
        }
        return null;
    }
}
