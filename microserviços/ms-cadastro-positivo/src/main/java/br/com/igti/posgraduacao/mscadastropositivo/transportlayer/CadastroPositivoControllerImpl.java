package br.com.igti.posgraduacao.mscadastropositivo.transportlayer;

import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;
import br.com.igti.posgraduacao.mscadastropositivo.exceptions.ExceptionUtil;
import br.com.igti.posgraduacao.mscadastropositivo.exceptions.ResourceException;
import br.com.igti.posgraduacao.mscadastropositivo.interactors.CadastroPositivoUseCase;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.documentacao.openapi.CadastroPositivoController;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.input.PositivoInput;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.mappers.PositivoMapper;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.output.PositivoOutput;
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
public class CadastroPositivoControllerImpl implements CadastroPositivoController {

    private final CadastroPositivoUseCase cadastroPositivoUseCase;

    public CadastroPositivoControllerImpl(CadastroPositivoUseCase cadastroPositivoUseCase) {
        this.cadastroPositivoUseCase = cadastroPositivoUseCase;
    }

    @Override
    public ResponseEntity<List<PositivoOutput>> getAllPositivos() {
        List<Positivo> positivos = null;
        try {
            positivos = cadastroPositivoUseCase.getAll();
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(PositivoMapper.INSTANCE.mapListOutput(positivos));
    }
    @Override
    public ResponseEntity<PositivoOutput> getPositivoById(
            @Parameter(name = "id", description = "Id da Transacao", required = true)
            @PathVariable("id") String id) {
        Positivo positivo = null;
        try {
            positivo = cadastroPositivoUseCase.getPositivoById(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(PositivoMapper.INSTANCE.mapOutput(positivo));
    }
    @Override
    public ResponseEntity<PositivoOutput> getPositivoByCpf(
            @Parameter(name = "cpf", description = "Número do CPF do Negativado", required = true)
            @PathVariable("cpf") String cpf) {
        Positivo positivo = null;
        try {
            positivo = cadastroPositivoUseCase.getPositivoByCpf(cpf);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.ok(PositivoMapper.INSTANCE.mapOutput(positivo));
    }

    @Override
    public ResponseEntity<PositivoOutput> inserirPositivo(@RequestBody @Valid PositivoInput positivoInput) {
        Positivo positivo = null;
        try {
            positivo = cadastroPositivoUseCase.inserirPositivo(PositivoMapper.INSTANCE.map(positivoInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(PositivoMapper.INSTANCE.mapOutput(positivo));
    }

    @Override
    public ResponseEntity<PositivoOutput> atualizarPositivo(@Parameter(name = "id", description = "Número do Id para cadastro positivo", required = true)
                                                                @PathVariable("id") String id,
                                                                @Parameter(description = "Cadastro positivo para atualizacao", required = true, schema = @Schema(implementation = PositivoInput.class))
                                                                @Valid @RequestBody PositivoInput positivoInput) {

        Positivo positivo = null;
        try {
            positivo = cadastroPositivoUseCase.atualizarPositivo(PositivoMapper.INSTANCE.map(positivoInput), id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(PositivoMapper.INSTANCE.mapOutput(positivo));
    }

    @Override
    public ResponseEntity excluirPositivo(@PathVariable String id) {
        try {
            cadastroPositivoUseCase.excluirPositivo(id);
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
