package br.com.igti.posgraduacao.transportlayer.mappers;

import br.com.igti.posgraduacao.entities.OrquestradorRespostaSolicitarAberturaConta;
import br.com.igti.posgraduacao.entities.OrquestradorSolicitarAberturaConta;
import br.com.igti.posgraduacao.entities.Process;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorSolicitarAberturaContaInput;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorSolicitarAberturaContaOutput;
import br.com.igti.posgraduacao.transportlayer.output.ProcessOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface OrquestradorRespostaSolicitacaoAberturaContaMapper {
    OrquestradorRespostaSolicitacaoAberturaContaMapper INSTANCE = Mappers.getMapper(OrquestradorRespostaSolicitacaoAberturaContaMapper.class);

    OrquestradorSolicitarAberturaConta map(OrquestradorSolicitarAberturaContaInput orquestradorSolicitarAberturaContaInput);

    @Mapping(source = "process.processId", target = "processOutput.processId")
    @Mapping(source = "process.bussinessKey", target = "processOutput.bussinessKey")
    OrquestradorSolicitarAberturaContaOutput mapOutput(OrquestradorRespostaSolicitarAberturaConta orquestradorRespostaSolicitarAberturaConta);
    ProcessOutput map(Process process);
    default List<OrquestradorSolicitarAberturaContaOutput> mapListOutput(List<OrquestradorRespostaSolicitarAberturaConta> solicitarRespostaAberturaContas){
        final List<OrquestradorSolicitarAberturaContaOutput> solicitarAberturaContasList = new ArrayList<>();
        for (OrquestradorRespostaSolicitarAberturaConta orquestradorRespostaSolicitarAberturaConta: solicitarRespostaAberturaContas) {
            OrquestradorSolicitarAberturaContaOutput orquestradorSolicitarAberturaContaOutput = OrquestradorRespostaSolicitacaoAberturaContaMapper.INSTANCE.mapOutput(orquestradorRespostaSolicitarAberturaConta);
            solicitarAberturaContasList.add(orquestradorSolicitarAberturaContaOutput);
        }
        return solicitarAberturaContasList;
    }


}
