package br.com.igti.posgraduacao.interactors;

import br.com.igti.posgraduacao.entities.OrquestradorRespostaSolicitarAberturaConta;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.repositories.OrquestradorSolicitarAberturaContaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrquestradorSolicitarAberturaContaUseCase {

    private final OrquestradorSolicitarAberturaContaRepository orquestradorSolicitarAberturaContaRepository;

    public OrquestradorSolicitarAberturaContaUseCase(OrquestradorSolicitarAberturaContaRepository orquestradorSolicitarAberturaContaRepository) {
        this.orquestradorSolicitarAberturaContaRepository = orquestradorSolicitarAberturaContaRepository;
    }

    public OrquestradorRespostaSolicitarAberturaConta solicitarAberturaDeConta(OrquestradorSolicitarAberturaConta orquestradorSolicitarAberturaConta){
         OrquestradorRespostaSolicitarAberturaConta orquestradorRespostaSolicitarAberturaConta =  orquestradorSolicitarAberturaContaRepository.solicitarAberturaDeConta(orquestradorSolicitarAberturaConta);
         return orquestradorRespostaSolicitarAberturaConta;
    }
}