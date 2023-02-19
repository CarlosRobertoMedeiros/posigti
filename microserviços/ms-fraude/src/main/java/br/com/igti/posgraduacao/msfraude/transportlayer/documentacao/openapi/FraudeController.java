package br.com.igti.posgraduacao.msfraude.transportlayer.documentacao.openapi;

import br.com.igti.posgraduacao.msfraude.transportlayer.input.FraudeInput;
import br.com.igti.posgraduacao.msfraude.transportlayer.output.FraudeOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Fraude", description = "Servicos de Verificacao de Fraude")
@RequestMapping(value = "/api/v1")
public interface FraudeController {

    @Operation(summary = "Verifica se esse email foi utilizado para fraudes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/email/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getEmailFraudado (
            @Parameter(name = "email", description = "Informe o email", required = true)
            @PathVariable("email") String email
    );

    @Operation(summary = "Verifica se esse telefone foi utilizado para fraudes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/telefone/{telefone}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getTelefoneFraudado (
            @Parameter(name = "telefone", description = "Informe o telefone", required = true)
            @PathVariable("telefone") String telefone
    );

    @Operation(summary = "Verifica se esse cep foi utilizado para fraudes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fraude Nao Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "400", description = "Fraude Identificada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementada",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @GetMapping(value = "/fraude/cep/{cep}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Valid
    ResponseEntity<Boolean> getCepFraudado (
            @Parameter(name = "cep", description = "Informe o cep", required = true)
            @PathVariable("cep") String cep
    );

    @Operation(summary = "insere informações de Fraudes Identificadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de Fraudes",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FraudeOutput.class))}),
            @ApiResponse(responseCode = "500", description = "Erro interno",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "501", description = "Ainda não implementado",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Void.class))}),

    })
    @PostMapping(value = "/fraude", produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<FraudeOutput> inserirFraude(@Valid @RequestBody FraudeInput fraudeInput);

}
