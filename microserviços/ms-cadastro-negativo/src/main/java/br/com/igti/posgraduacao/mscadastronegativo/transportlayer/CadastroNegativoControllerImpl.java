package br.com.igti.posgraduacao.mscadastronegativo.transportlayer;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.exceptions.ExceptionUtil;
import br.com.igti.posgraduacao.mscadastronegativo.exceptions.ResourceException;
import br.com.igti.posgraduacao.mscadastronegativo.interactors.CadastroNegativoUseCase;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.documentacao.openapi.CadastroNegativoController;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.input.NegativadoInput;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.mappers.NegativadoMapper;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.output.NegativadoOutput;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
        try {
            negativados = cadastroNegativoUseCase.getAll();
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapListOutput(negativados));

    }

    @Override
    public ResponseEntity<NegativadoOutput> getByCpf(
            @Parameter(name = "cpf", description = "Número do CPF do Negativado", required = true)
            @PathVariable("cpf") String cpf) {
        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.getByCpf(cpf);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }

    @Override
    public ResponseEntity<NegativadoOutput> post(@Valid @RequestBody NegativadoInput negativadoInput) {
        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.post(NegativadoMapper.INSTANCE.map(negativadoInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }

    //TODO: Implementar o put e o delete
}
