package br.com.igti.posgraduacao.repositories;

import br.com.igti.posgraduacao.transportlayer.input.OrquestradorRequestDto;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorResponseDto;

public interface OrquestradorRepository {
    OrquestradorResponseDto solicitarAberturaDeConta(OrquestradorRequestDto orquestradorRequestDto);
}
