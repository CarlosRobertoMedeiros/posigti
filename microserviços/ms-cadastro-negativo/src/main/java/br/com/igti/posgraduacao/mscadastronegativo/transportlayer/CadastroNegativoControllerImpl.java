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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<NegativadoOutput>> getAllNegativados() {
        List<Negativado> negativados = null;
        try {
            negativados = cadastroNegativoUseCase.getAll();
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapListOutput(negativados));
    }
    @Override
    public ResponseEntity<NegativadoOutput> getNegativadoById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id) {
        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.getNegativadoById(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }
    @Override
    public ResponseEntity<NegativadoOutput> getNegativadoByCpf(
            @Parameter(name = "cpf", description = "Número do CPF do Negativado", required = true)
            @PathVariable("cpf") String cpf) {
        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.getNegativadoByCpf(cpf);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }

    @Override
    public ResponseEntity<NegativadoOutput> inserirNegativado(@RequestBody @Valid NegativadoInput negativadoInput) {
        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.inserirNegativado(NegativadoMapper.INSTANCE.map(negativadoInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }

    @Override
    public ResponseEntity<NegativadoOutput> atualizarNegativado(@Parameter(name = "id", description = "Número do Id para negativacao", required = true)
                                                                @PathVariable("id") String id,
                                                                @Parameter(description = "Negativado para atualizacao", required = true, schema = @Schema(implementation = NegativadoInput.class))
                                                                @Valid @RequestBody NegativadoInput negativadoInput) {

        Negativado negativado = null;
        try {
            negativado = cadastroNegativoUseCase.atualizarNegativado(NegativadoMapper.INSTANCE.map(negativadoInput), id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(NegativadoMapper.INSTANCE.mapOutput(negativado));
    }

    @Override
    public ResponseEntity excluirNegativado(@PathVariable String id) {
        try {
            cadastroNegativoUseCase.excluirNegativados(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
