package br.com.igti.posgraduacao.transportlayer;

import br.com.igti.posgraduacao.interactors.OrquestradorUseCase;
import br.com.igti.posgraduacao.transportlayer.documentacao.openapi.OrquestradorController;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorRequestDto;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrquestradorControllerImpl implements OrquestradorController {

    private final OrquestradorUseCase orquestradorUseCase;

    public OrquestradorControllerImpl(OrquestradorUseCase orquestradorUseCase) {
        this.orquestradorUseCase = orquestradorUseCase;
    }

    @Override
    public ResponseEntity<OrquestradorResponseDto> solicitarAberturaDeConta(OrquestradorRequestDto orquestradorRequestDto) {
        System.out.println(orquestradorRequestDto);
        OrquestradorResponseDto orquestradorResponseDto = orquestradorUseCase.solicitarAberturaDeConta(orquestradorRequestDto);
        return new ResponseEntity<OrquestradorResponseDto>(HttpStatus.NOT_IMPLEMENTED);
    }
}
