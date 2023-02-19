package br.com.igti.posgraduacao.msfraude.transportlayer;

import br.com.igti.posgraduacao.msfraude.entities.Fraude;
import br.com.igti.posgraduacao.msfraude.exceptions.ExceptionUtil;
import br.com.igti.posgraduacao.msfraude.exceptions.ResourceException;
import br.com.igti.posgraduacao.msfraude.interactors.FraudeUseCase;
import br.com.igti.posgraduacao.msfraude.transportlayer.documentacao.openapi.FraudeController;
import br.com.igti.posgraduacao.msfraude.transportlayer.input.FraudeInput;
import br.com.igti.posgraduacao.msfraude.transportlayer.mappers.FraudeMapper;
import br.com.igti.posgraduacao.msfraude.transportlayer.output.FraudeOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraudeControllerImpl implements FraudeController {

    private final FraudeUseCase fraudeUseCase;

    public FraudeControllerImpl(FraudeUseCase fraudeUseCase) {
        this.fraudeUseCase = fraudeUseCase;
    }

    @Override
    public ResponseEntity<Boolean> getEmailFraudado(String email) {
        Boolean emailValido =  fraudeUseCase.getEmailFraudado(email);
        return ResponseEntity.ok(emailValido);
    }

    @Override
    public ResponseEntity<Boolean> getTelefoneFraudado(String telefone) {
        Boolean telefoneValido = fraudeUseCase.getTelefoneFraudado(telefone);
        return ResponseEntity.ok(telefoneValido);
    }

    @Override
    public ResponseEntity<Boolean> getCepFraudado(String cep) {
        Boolean cepValido =  fraudeUseCase.getCepFraudado(cep);
        return ResponseEntity.ok(cepValido);
    }

    @Override
    public ResponseEntity<FraudeOutput> inserirFraude(FraudeInput fraudeInput) {
        Fraude fraude = null;
        try {
            fraude = fraudeUseCase.inserirFraude(FraudeMapper.INSTANCE.map(fraudeInput));
        }catch (ResourceException e){
            ExceptionUtil.throwException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(FraudeMapper.INSTANCE.mapOutput(fraude));
    }
}
