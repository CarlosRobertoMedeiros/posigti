package br.com.igti.posgraduacao.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.transportlayer.input.OrquestradorSolicitarAberturaContaInput;
import br.com.igti.posgraduacao.transportlayer.output.OrquestradorSolicitarAberturaContaOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "Orquestrador-Camunda", description = "Interface de Comunicação com Orquestrador")
@RequestMapping(value = "/api/v1")
public interface OrquestradorSolicitarAberturaContaController {
    @Operation(summary = "Interface para orquestrar o Processo do Camunda para Abertura de Conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisicao Realizada com Sucesso",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = OrquestradorSolicitarAberturaContaOutput.class))}),
            @ApiResponse(responseCode = "400", description = "Requisicao Realizada com Erro",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Requisicao Realizada com Erro",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/solicitar-abertura-conta", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<OrquestradorSolicitarAberturaContaOutput> solicitarAberturaDeConta(@Valid @RequestBody OrquestradorSolicitarAberturaContaInput orquestradorSolicitarAberturaContaInput);
}

