package br.com.igti.posgraduacao.mshubmensagens.transportlayer.mappers;

import br.com.igti.posgraduacao.mshubmensagens.entities.Email;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.input.EmailInput;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.output.EmailOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    Email map(EmailInput emailInput);

    EmailOutput mapOutput(Email email);

    default List<EmailOutput> mapListOutput(List<Email> emails){
        final List<EmailOutput> emailOutputList = new ArrayList<>();
        for (Email email: emails) {
            EmailOutput emailOutput = EmailMapper.INSTANCE.mapOutput(email);
            emailOutputList.add(emailOutput);
        }
        return emailOutputList;
    }


}
