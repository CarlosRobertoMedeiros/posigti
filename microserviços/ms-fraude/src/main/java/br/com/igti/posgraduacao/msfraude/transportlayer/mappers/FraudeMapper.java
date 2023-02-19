package br.com.igti.posgraduacao.msfraude.transportlayer.mappers;

import br.com.igti.posgraduacao.msfraude.entities.Fraude;
import br.com.igti.posgraduacao.msfraude.transportlayer.input.FraudeInput;
import br.com.igti.posgraduacao.msfraude.transportlayer.output.FraudeOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface FraudeMapper {
    FraudeMapper INSTANCE = Mappers.getMapper(FraudeMapper.class);

    Fraude map(FraudeInput fraudeInput);

    FraudeOutput mapOutput(Fraude fraude);

    default List<FraudeOutput> mapListOutput(List<Fraude> fraudes){
        final List<FraudeOutput> fraudeOutputList = new ArrayList<>();
        for (Fraude fraude: fraudes) {
            FraudeOutput fraudeOutput = FraudeMapper.INSTANCE.mapOutput(fraude);
            fraudeOutputList.add(fraudeOutput);
        }
        return fraudeOutputList;
    }


}
