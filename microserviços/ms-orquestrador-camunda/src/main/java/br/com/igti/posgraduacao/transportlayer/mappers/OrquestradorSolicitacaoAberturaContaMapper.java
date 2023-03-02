package br.com.igti.posgraduacao.transportlayer.mappers;

import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorSolicitarAberturaContaInput;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorSolicitarAberturaContaOutput;
import br.com.igti.posgraduacao.transportlayer.output.ProcessOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrquestradorSolicitacaoAberturaContaMapper {
    OrquestradorSolicitacaoAberturaContaMapper INSTANCE = Mappers.getMapper(OrquestradorSolicitacaoAberturaContaMapper.class);

    OrquestradorSolicitarAberturaConta map(OrquestradorSolicitarAberturaContaInput orquestradorSolicitarAberturaContaInput);

    OrquestradorSolicitarAberturaContaOutput mapOutput(OrquestradorSolicitarAberturaConta orquestradorSolicitarAberturaConta);




    default List<OrquestradorSolicitarAberturaContaOutput> mapListOutput(List<OrquestradorSolicitarAberturaConta> solicitarAberturaContas){
        final List<OrquestradorSolicitarAberturaContaOutput> solicitarAberturaContasList = new ArrayList<>();
        for (OrquestradorSolicitarAberturaConta orquestradorSolicitarAberturaConta: solicitarAberturaContas) {
            OrquestradorSolicitarAberturaContaOutput orquestradorSolicitarAberturaContaOutput = OrquestradorSolicitacaoAberturaContaMapper.INSTANCE.mapOutput(orquestradorSolicitarAberturaConta);
            solicitarAberturaContasList.add(orquestradorSolicitarAberturaContaOutput);
        }
        return solicitarAberturaContasList;
    }


}
