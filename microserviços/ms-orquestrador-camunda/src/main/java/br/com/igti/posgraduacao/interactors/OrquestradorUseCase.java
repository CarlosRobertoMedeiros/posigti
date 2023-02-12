package br.com.igti.posgraduacao.interactors;

import br.com.igti.posgraduacao.repositories.OrquestradorRepository;
import br.com.igti.posgraduacao.transportlayer.input.OrquestradorRequestDto;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorResponseDto;
import org.springframework.stereotype.Service;

@Service
public class OrquestradorUseCase {

    private final OrquestradorRepository orquestradorRepository;

    public OrquestradorUseCase(OrquestradorRepository orquestradorRepository) {
        this.orquestradorRepository = orquestradorRepository;
    }

    public OrquestradorResponseDto solicitarAberturaDeConta(OrquestradorRequestDto orquestradorRequestDto){
        return orquestradorRepository.solicitarAberturaDeConta(orquestradorRequestDto);
    }
}
