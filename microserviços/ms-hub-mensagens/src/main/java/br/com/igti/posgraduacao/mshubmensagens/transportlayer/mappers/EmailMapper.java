package br.com.igti.posgraduacao.mshubmensagens.transportlayer.mappers;

import br.com.igti.posgraduacao.mshubmensagens.entities.EmailHub;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.input.EmailHubInput;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.output.EmailHubOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    EmailHub map(EmailHubInput emailHubInput);

    EmailHubOutput mapOutput(EmailHub emailHub);

    default List<EmailHubOutput> mapListOutput(List<EmailHub> emails){
        final List<EmailHubOutput> emailOutputList = new ArrayList<>();
        for (EmailHub emailHub: emails) {
            EmailHubOutput emailHubOutput = EmailMapper.INSTANCE.mapOutput(emailHub);
            emailOutputList.add(emailHubOutput);
        }
        return emailOutputList;
    }


}
