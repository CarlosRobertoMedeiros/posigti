package br.com.igti.posgraduacao.mscadastronegativo.transportlayer;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.interactors.CadastroNegativoUseCase;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.documentacao.openapi.CadastroNegativoController;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.input.NegativadoInput;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.output.NegativadoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CadastroNegativoControllerImpl implements CadastroNegativoController {

    private final CadastroNegativoUseCase cadastroNegativoUseCase;

    public CadastroNegativoControllerImpl(CadastroNegativoUseCase cadastroNegativoUseCase) {
        this.cadastroNegativoUseCase = cadastroNegativoUseCase;
    }

    @Override
    public ResponseEntity<List<NegativadoOutput>> getAll() {
        List<Negativado> negativados = null;
//        try {
//            negativados = cadastroNegativoUseCase.getAll();
//        }catch (ResourceException e){
//            ExceptionUtil.throwException(e);
//        }
//        return ResponseEntity.of(NegativadoMapper.INSTANCE.mapListOutput(negativados));
        return null;
    }

    @Override
    public ResponseEntity<NegativadoOutput> getByCpf(String cpf) {
        return null;
    }

    @Override
    public ResponseEntity<NegativadoOutput> post(NegativadoInput negativadoInput) {
        return null;
    }
}
