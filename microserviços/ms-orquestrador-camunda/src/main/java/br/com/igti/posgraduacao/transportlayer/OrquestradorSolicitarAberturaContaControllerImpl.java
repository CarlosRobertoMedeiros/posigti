package br.com.igti.posgraduacao.transportlayer;

import br.com.igti.posgraduacao.entities.OrquestradorRespostaSolicitarAberturaConta;
import br.com.igti.posgraduacao.exception.ExceptionUtil;
import br.com.igti.posgraduacao.exception.ResourceException;
import br.com.igti.posgraduacao.interactors.OrquestradorSolicitarAberturaContaUseCase;
import br.com.igti.posgraduacao.transportlayer.documentacao.openapi.OrquestradorSolicitarAberturaContaController;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorSolicitarAberturaContaInput;
import br.com.igti.posgraduacao.transportlayer.mappers.OrquestradorRespostaSolicitacaoAberturaContaMapper;
import br.com.igti.posgraduacao.transportlayer.mappers.OrquestradorSolicitacaoAberturaContaMapper;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorSolicitarAberturaContaOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrquestradorSolicitarAberturaContaControllerImpl implements OrquestradorSolicitarAberturaContaController {

    private final OrquestradorSolicitarAberturaContaUseCase orquestradorSolicitarAberturaContaUseCase;

    public OrquestradorSolicitarAberturaContaControllerImpl(OrquestradorSolicitarAberturaContaUseCase orquestradorSolicitarAberturaContaUseCase) {
        this.orquestradorSolicitarAberturaContaUseCase = orquestradorSolicitarAberturaContaUseCase;
    }

    @Override
    public ResponseEntity<OrquestradorSolicitarAberturaContaOutput> solicitarAberturaDeConta(OrquestradorSolicitarAberturaContaInput orquestradorSolicitarAberturaContaInput) {
        System.out.println(orquestradorSolicitarAberturaContaInput);
        OrquestradorRespostaSolicitarAberturaConta orquestradorRespostaSolicitarAberturaConta = null;
        try {
            orquestradorRespostaSolicitarAberturaConta = orquestradorSolicitarAberturaContaUseCase.solicitarAberturaDeConta(OrquestradorSolicitacaoAberturaContaMapper.INSTANCE.map(orquestradorSolicitarAberturaContaInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(OrquestradorRespostaSolicitacaoAberturaContaMapper.INSTANCE.mapOutput(orquestradorRespostaSolicitarAberturaConta));
    }
}
