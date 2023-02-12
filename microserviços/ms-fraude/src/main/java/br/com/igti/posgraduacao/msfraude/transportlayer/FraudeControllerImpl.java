package br.com.igti.posgraduacao.msfraude.transportlayer;

import br.com.igti.posgraduacao.msfraude.interactors.FraudeUseCase;
import br.com.igti.posgraduacao.msfraude.transportlayer.documentacao.openapi.FraudeController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FraudeControllerImpl implements FraudeController {

    private final FraudeUseCase fraudeUseCase;

    public FraudeControllerImpl(FraudeUseCase fraudeUseCase) {
        this.fraudeUseCase = fraudeUseCase;
    }

    @Override
    public ResponseEntity<Boolean> getEmailValido(String email) {
        Boolean emailValido =  fraudeUseCase.getEmailValido(email);
        return ResponseEntity.ok(emailValido);
    }

    @Override
    public ResponseEntity<Boolean> getTelefoneValido(String telefone) {
        Boolean telefoneValido = fraudeUseCase.getTelefoneValido(telefone);
        return ResponseEntity.ok(telefoneValido);
    }

    @Override
    public ResponseEntity<Boolean> getCepValido(String cep) {
        Boolean cepValido =  fraudeUseCase.getCepValido(cep);
        return ResponseEntity.ok(cepValido);
    }
}
