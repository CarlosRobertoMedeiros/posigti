package br.com.igti.posgraduacao.mscadastronegativo.transportlayer.mappers;

import br.com.igti.posgraduacao.mscadastronegativo.entities.Negativado;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.input.NegativadoInput;
import br.com.igti.posgraduacao.mscadastronegativo.transportlayer.output.NegativadoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface NegativadoMapper {
    NegativadoMapper INSTANCE = Mappers.getMapper(NegativadoMapper.class);

    Negativado map(NegativadoInput negativadoInput);

    NegativadoOutput mapOutput(Negativado negativado);

    default List<NegativadoOutput> mapListOutput(List<Negativado> negativados){
        final List<NegativadoOutput> negativadoOutputList = new ArrayList<>();
        for (Negativado negativado: negativados) {
            NegativadoOutput negativadoOutput = NegativadoMapper.INSTANCE.mapOutput(negativado);
            negativadoOutputList.add(negativadoOutput);
        }
        return negativadoOutputList;
    }


}
