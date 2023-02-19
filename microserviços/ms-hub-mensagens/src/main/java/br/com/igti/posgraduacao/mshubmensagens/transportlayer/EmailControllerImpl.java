package br.com.igti.posgraduacao.mshubmensagens.transportlayer;

import br.com.igti.posgraduacao.mshubmensagens.entities.Email;
import br.com.igti.posgraduacao.mshubmensagens.exceptions.ExceptionUtil;
import br.com.igti.posgraduacao.mshubmensagens.exceptions.ResourceException;
import br.com.igti.posgraduacao.mshubmensagens.interactors.EmailUseCase;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.documentacao.openapi.EmailController;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.input.EmailInput;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.mappers.EmailMapper;
import br.com.igti.posgraduacao.mshubmensagens.transportlayer.output.EmailOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EmailControllerImpl implements EmailController {

    private final EmailUseCase emailUseCase;

    public EmailControllerImpl(EmailUseCase emailUseCase) {
        this.emailUseCase = emailUseCase;
    }


    @Override
    public ResponseEntity<EmailOutput> enviarEmail(EmailInput emailInput) {
        Email email = null;
        try {
            email = emailUseCase.enviarEmail(EmailMapper.INSTANCE.map(emailInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(EmailMapper.INSTANCE.mapOutput(email));
    }
}
