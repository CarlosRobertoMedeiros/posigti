package br.com.igti.posgraduacao.mscadastropositivo.transportlayer.mappers;

import br.com.igti.posgraduacao.mscadastropositivo.entities.Positivo;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.input.PositivoInput;
import br.com.igti.posgraduacao.mscadastropositivo.transportlayer.output.PositivoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PositivoMapper {
    PositivoMapper INSTANCE = Mappers.getMapper(PositivoMapper.class);

    Positivo map(PositivoInput positivoInput);

    PositivoOutput mapOutput(Positivo positivo);

    default List<PositivoOutput> mapListOutput(List<Positivo> positivos){
        final List<PositivoOutput> positivoOutputList = new ArrayList<>();
        for (Positivo positivo: positivos) {
            PositivoOutput positivoOutput = PositivoMapper.INSTANCE.mapOutput(positivo);
            positivoOutputList.add(positivoOutput);
        }
        return positivoOutputList;
    }


}
